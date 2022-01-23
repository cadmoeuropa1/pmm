package com.rdpp.ej2basesdedatos.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.rdpp.ej2basesdedatos.R
import com.rdpp.ej2basesdedatos.database.LawFirmDAO
import com.rdpp.ej2basesdedatos.databinding.ActivityAddProcedureBinding
import com.rdpp.ej2basesdedatos.dataclasses.Case
import com.rdpp.ej2basesdedatos.dataclasses.Procedure
import java.text.SimpleDateFormat
import java.util.*

class AddProcedureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProcedureBinding
    private lateinit var database: LawFirmDAO
    private lateinit var case: Case
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProcedureBinding.inflate(layoutInflater)
        database = LawFirmDAO(this)
        case = intent.getParcelableExtra<Case>("case")!!
        setContentView(binding.root)
        binding.inputDate.text = getCurrentDateTime().toString("DD/MM/YYYY")



        binding.btnAddNew.setOnClickListener {
            val procedure = Procedure(
                case.caseNum!!,
                binding.inputDate.text.toString(),
                binding.editTextDetails.text.toString(),
                binding.editTextExecuted.text.toString()
            )
            validateAndAddProcedure(procedure)
        }
    }

    private fun validateAndAddProcedure(procedure: Procedure) {
        val result: Long
        if (procedure.executed != "YES" && procedure.executed != "NO"){
            Snackbar.make(binding.root, getString(R.string.error_procedure_add_executed), Snackbar.LENGTH_SHORT).show()
        }else{
            result = database.addProcedure(procedure)
            if (result == -1L){
                Snackbar.make(binding.root, getString(R.string.error_procedure_not_saved), Snackbar.LENGTH_SHORT).show()
            }
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