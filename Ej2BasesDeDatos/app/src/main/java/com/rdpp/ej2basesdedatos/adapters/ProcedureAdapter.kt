package com.rdpp.ej2basesdedatos.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rdpp.ej2basesdedatos.R
import com.rdpp.ej2basesdedatos.databinding.ItemProcedureBinding
import com.rdpp.ej2basesdedatos.dataclasses.Procedure
import com.rdpp.ej2basesdedatos.interfaces.ProcedureEventListener
import java.text.SimpleDateFormat
import java.util.*

class ProcedureAdapter(
    private var procedures: MutableList<Procedure>,
    private var listenerProcedure: ProcedureEventListener
) : RecyclerView.Adapter<ProcedureAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemProcedureBinding.bind(view)
        fun setListener(procedure: Procedure) {
            binding.card.setOnLongClickListener {
                listenerProcedure.changeStatus(procedure)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_procedure, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val procedure = procedures[position]
        with(holder) {
            setListener(procedure)
            binding.txtDate.text = binding.txtDate.toString() + getCurrentDateTime().toString()
            binding.txtProcedure.text = binding.txtProcedure.text.toString() + procedure.caseNum
            binding.txtDetails.text = binding.txtDetails.text.toString() + procedure.details
            binding.txtExecuted.text = binding.txtExecuted.text.toString() + procedure.executed
        }
    }

    fun setProcedures(proceduresR: MutableList<Procedure>?) {
        if (proceduresR != null) {
            this.procedures = proceduresR
        }
    }

    override fun getItemCount(): Int = procedures.size

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
}