package com.rdpp.dam_raulpaniti_ejercicio2_bloque1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mse.dam2_raulpaniti_ejercicio2_bloque1.R
import com.mse.dam2_raulpaniti_ejercicio2_bloque1.databinding.MonumentCardLayoutBinding


class MonumentAdapter(private val monuments: List<Monument>, private val evt: EventListener) :
    RecyclerView.Adapter<MonumentAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(context).inflate(R.layout.monument_card_layout, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val monument = monuments.get(position)
        with(holder) {
            setListener(monument, (position + 1))
            binding.txtMonument.text = monument.name
            binding.monumentDirection.text = monument.location
            Glide.with(context).load(monument.image).centerCrop().diskCacheStrategy(
                DiskCacheStrategy.ALL).into(binding.imgMonument)
            setLongListener(monument,(position+1))
            binding.btnCall.setOnClickListener {
                evt.call(monument,position)
            }
            binding.btnMail.setOnClickListener {
                evt.sendMail(monument,position)
            }
        }
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = MonumentCardLayoutBinding.bind(view)
        fun setListener(monument: Monument, position: Int) {
            binding.root.setOnClickListener {
                evt.onClickListener(monument, position)
            }
        }
        fun setLongListener(monument: Monument, position: Int) {
            binding.card.setOnLongClickListener {
                binding.card.setChecked(!binding.card.isChecked)
                true
            }
        }
    }

    override fun getItemCount(): Int = monuments.size
}

