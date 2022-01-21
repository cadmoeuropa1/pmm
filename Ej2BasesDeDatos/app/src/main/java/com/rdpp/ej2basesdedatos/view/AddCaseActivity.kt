package com.rdpp.ej2basesdedatos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rdpp.ej2basesdedatos.databinding.ActivityAddCaseBinding
import java.text.SimpleDateFormat
import java.util.*

class AddCaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txtDate.text = getCurrentDateTime().toString("DD/MM/YYYY")

        binding.btnAddNew.setOnClickListener {
            val name: String = binding.txtName.text.toString()
            val date: String = binding.txtDate.text.toString()
            val details: String = binding.txtDetails.text.toString()
            val lawyer: String = binding.spnLawyers.selectedItem.toString()
            addNewCase(name, date, details, lawyer)
        }
    }

    private fun addNewCase(name: String, date: String, details: String, lawyer: String) {
        TODO("Not yet implemented")
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
}