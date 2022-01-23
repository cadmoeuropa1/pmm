package com.rdpp.bd2PanitiRaul.view

import android.os.Bundle
import android.widget.CursorAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.rdpp.bd2PanitiRaul.R
import com.rdpp.bd2PanitiRaul.database.LawFirmDAO
import com.rdpp.bd2PanitiRaul.databinding.ActivityAddCaseBinding
import com.rdpp.bd2PanitiRaul.dataclasses.Case
import java.text.SimpleDateFormat
import java.util.*

class AddCaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCaseBinding
    private lateinit var database: LawFirmDAO
    private lateinit var adapterS: CursorAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txtDate.text = getCurrentDateTime().toString("DD/MM/YYYY")
        database = LawFirmDAO(this)


        binding.btnAddNew.setOnClickListener {

            val name: String = binding.txtName.text.toString()
            val date: String = binding.txtDate.text.toString()
            val details: String = binding.txtDetails.text.toString()
            val lawyer: String = binding.txtLawyer.text.toString()
            if (database.checkUser(lawyer)) {
                addNewCase(name, date, details, lawyer)
            } else {
                Snackbar.make(
                    binding.root,
                    getString(R.string.reg_Num_not_found),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun addNewCase(name: String, date: String, details: String, lawyer: String) {
        val result = database.addCase(Case(name, date, details, lawyer))
        if (result != -1L) {
            Snackbar.make(
                binding.root,
                getString(R.string.case_added_correct),
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            Snackbar.make(binding.root, getString(R.string.case_added_error), Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
}