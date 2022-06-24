package com.rdpp.komorebi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rdpp.komorebi.R
import com.rdpp.komorebi.databinding.WorkshopCardLayoutBinding
import com.rdpp.komorebi.listener.WorkshopEventListener
import com.rdpp.komorebi.model.Workshop

class WorkshopAdapter(
    private var workshops: MutableList<Workshop>,
    private var listener: WorkshopEventListener
) : RecyclerView.Adapter<WorkshopAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = WorkshopCardLayoutBinding.bind(view)
        fun setListener(position: Int, workshop: Workshop) {
            binding.btnShare.setOnClickListener {
                listener.shareWorkshop(workshop)
            }
            binding.btnView.setOnClickListener {
                listener.viewWorkshop(workshop)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.workshop_card_layout, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workshop = this.workshops[position]
        with(holder) {
            setListener(position, workshop)
            binding.txtTitle.text = workshop.name
            binding.txtDate.text = workshop.date
            Glide.with(context).load(workshop.imgUrl).centerCrop().diskCacheStrategy(
                DiskCacheStrategy.ALL
            ).into(binding.imgArticle)
        }
    }

    override fun getItemCount(): Int = workshops.size

    fun setWorkshops(workshopsR: MutableList<Workshop>?) {
        if (workshopsR != null) {
            workshops = workshopsR
        }
    }
}
