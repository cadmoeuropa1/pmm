package com.rdpp.ej2basesdedatos.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rdpp.ej2basesdedatos.R
import com.rdpp.ej2basesdedatos.databinding.ItemCaseBinding
import com.rdpp.ej2basesdedatos.dataclasses.Case
import com.rdpp.ej2basesdedatos.interfaces.CaseEventListener

class CaseAdapter(
    private var cases: MutableList<Case>, private var listenerCase: CaseEventListener,
) : RecyclerView.Adapter<CaseAdapter.ViewHolder>() {
    private lateinit var context: Context


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCaseBinding.bind(view)
        fun setListener(case: Case) {
            binding.card.setOnClickListener {
                listenerCase.showDetails(case)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            context = parent.context
            val view = LayoutInflater.from(context).inflate(R.layout.item_case, parent, false)
            return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val case = cases[position]
        with(holder) {
            setListener(case)
            binding.caseName.text = case.name
            binding.caseNumber.text = "Case Nº: " + case.caseNum
        }
    }

    fun setCases(casesR: MutableList<Case>?) {
        if (casesR != null) {
            this.cases = casesR
        }
    }

    fun update(case: Case) {
        val index = cases.indexOf(case)
        if (index != -1) {
            cases[index] = case
            notifyItemChanged(index)
        }
    }

    override fun getItemCount(): Int = cases.size

}