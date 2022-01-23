package com.rdpp.bd2PanitiRaul.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.rdpp.bd2PanitiRaul.adapters.ProcedureAdapter
import com.rdpp.bd2PanitiRaul.database.LawFirmDAO
import com.rdpp.bd2PanitiRaul.databinding.ActivityCaseProceduresBinding
import com.rdpp.bd2PanitiRaul.dataclasses.Case
import com.rdpp.bd2PanitiRaul.dataclasses.Procedure
import com.rdpp.bd2PanitiRaul.interfaces.ProcedureEventListener
import java.text.SimpleDateFormat
import java.util.*

class CaseProceduresActivity : AppCompatActivity(), ProcedureEventListener {
    private lateinit var binding: ActivityCaseProceduresBinding
    private lateinit var adapterR: ProcedureAdapter
    private lateinit var layoutR: GridLayoutManager
    private lateinit var database: LawFirmDAO
    private lateinit var caseR: Case
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCaseProceduresBinding.inflate(layoutInflater)
        setContentView(binding.root)
        caseR = intent.getParcelableExtra<Case>("case")!!
        database = LawFirmDAO(this)
        setRecyclerView(caseR)

        binding.fabAddProcedure.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("NEW PROCEDURE")
            builder.setMessage("You are about to add a new procedure to the case, do you want to continue?")
            builder.setPositiveButton("CONTINUE",  DialogInterface.OnClickListener { _, _ ->
                val intent = Intent(this, AddProcedureActivity::class.java)
                intent.putExtra("case", caseR)
                startActivity(intent)
            })
            builder.setNegativeButton("CANCEL", null)
            builder.show()
        }
    }

    private fun setRecyclerView(case: Case) {
        adapterR = ProcedureAdapter(mutableListOf(), this)
        layoutR = GridLayoutManager(this, 1)
        val procedures: MutableList<Procedure> = database.getCaseProcedures(case.caseNum)
        adapterR.setProcedures(procedures)
        binding.recyclerProcedures.apply {
            setHasFixedSize(true)
            adapter = adapterR
            layoutManager = layoutR
        }


    }

    override fun changeStatus(procedure: Procedure) {
        database.updateProcedure(procedure)

    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

}