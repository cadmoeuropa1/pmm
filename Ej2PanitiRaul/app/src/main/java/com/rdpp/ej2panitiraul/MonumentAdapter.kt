package com.rdpp.ej2panitiraul

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rdpp.ej2panitiraul.databinding.MonumentCardLayoutBinding

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
            setListener(monument)
            binding.txtMonument.text = monument.name
            binding.monumentDirection.text = monument.location
            Glide.with(context).load(monument.image).centerCrop().diskCacheStrategy(
                DiskCacheStrategy.ALL
            ).into(binding.imgMonument)

        }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = MonumentCardLayoutBinding.bind(view)
        fun setListener(monument: Monument) {
            binding.root.setOnClickListener {
                evt.onClickListener(monument)
            }
            binding.btnCall.setOnClickListener {
                evt.call(monument)
            }
            binding.btnMail.setOnClickListener {
                evt.sendMail(monument)
            }
            binding.card.setOnLongClickListener{
                evt.onLongClick(monument)
                true
            }
        }
    }

    override fun getItemCount(): Int = monuments.size

}