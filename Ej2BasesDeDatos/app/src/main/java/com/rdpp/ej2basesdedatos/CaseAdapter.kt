package com.rdpp.ej2basesdedatos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rdpp.ej2basesdedatos.databinding.ItemCaseBinding
import com.rdpp.ej2basesdedatos.dataclasses.Case
import com.rdpp.ej2basesdedatos.interfaces.EventListener

class CaseAdapter(
    private var cases: MutableList<Case>, private var listener: EventListener,
) : RecyclerView.Adapter<CaseAdapter.ViewHolder>() {
    private lateinit var context: Context


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCaseBinding.bind(view)
        fun setListener(case: Case) {
            binding.card.setOnClickListener {
                listener.showDetails(case)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_case, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val case = cases.get(position)
        with(holder) {
            setListener(case)
            binding.caseName.text = case.name
            binding.caseNumber.text = "Case Nº: " + case.caseNum
        }
    }

    fun setCases(casesR: MutableList<Case>) {
        this.cases = casesR
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