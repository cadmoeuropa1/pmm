package com.rdpp.bd2PanitiRaul.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.rdpp.bd2PanitiRaul.adapters.CaseAdapter
import com.rdpp.bd2PanitiRaul.database.LawFirmDAO
import com.rdpp.bd2PanitiRaul.databinding.ActivityMainScreenBinding
import com.rdpp.bd2PanitiRaul.dataclasses.Case
import com.rdpp.bd2PanitiRaul.dataclasses.User
import com.rdpp.bd2PanitiRaul.interfaces.CaseEventListener

class MainScreen : AppCompatActivity(), CaseEventListener {

    private lateinit var binding: ActivityMainScreenBinding
    private lateinit var adapterR: CaseAdapter
    private lateinit var layoutR: GridLayoutManager
    private lateinit var database: LawFirmDAO
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        database = LawFirmDAO(this)
        user = intent.getParcelableExtra<User>("user")!!
        setContentView(binding.root)
        setRecyclerView(user)

        binding.fobAddCase.setOnClickListener() {
            val intent = Intent(this, AddCaseActivity::class.java)
            startActivityForResult(intent, 1)

        }
    }

    fun setRecyclerView(user: User) {
        when (user.type) {
            "L" -> {
                binding.fobAddCase.isGone = true
                adapterR = CaseAdapter(mutableListOf(), this)
                layoutR = GridLayoutManager(this, 1)
                setLawyerCases(user.reg_Num)
                binding.recyclerView.apply {
                    setHasFixedSize(true)
                    adapter = adapterR
                    layoutManager = layoutR
                }
            }
            "S" -> {
                adapterR = CaseAdapter(mutableListOf(), this)
                layoutR = GridLayoutManager(this, 1)
                setAllCases()
                binding.recyclerView.apply {
                    setHasFixedSize(true)
                    adapter = adapterR
                    layoutManager = layoutR
                }
            }
            else -> {}
        }
    }

    private fun setAllCases() {
        val cases = database.getAllCases()
        adapterR.setCases(cases)
    }

    private fun setLawyerCases(regNum: String) {
        val cases = database.getLawyerCases(regNum)
        adapterR.setCases(cases)
    }

    override fun showDetails(case: Case) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Case " + case.caseNum + " (" + case.name + ")")
        builder.setMessage("Case: ${case.caseNum} \nName: ${case.name} \nDate: ${case.date} \nDetails: ${case.details} \nLawyer (Registration Number): ${case.lawyer} ")
        builder.setPositiveButton("DISMISS", null)
        builder.setNegativeButton("PROCEDURES", DialogInterface.OnClickListener { _, _ ->
            val intent = Intent(this, CaseProceduresActivity::class.java)
            intent.putExtra("case", case)
            startActivity(intent)
        })
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}