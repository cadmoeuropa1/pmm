package com.rdpp.bd1panitiraul

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rdpp.bd1panitiraul.databinding.ItemApartmentBinding

class ApartmentAdapter(
    private val apartments: MutableList<Apartment>,
    private val listener: EventsListener,
) : RecyclerView.Adapter<ApartmentAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemApartmentBinding.bind(view)
        fun setListener(apartment: Apartment) {
            with(binding.root) {
                setOnClickListener { listener.edit(apartment.id) }
                setOnLongClickListener {
                    listener.deleteStore(apartment.id)
                    true
                }
            }
            binding.cbFavorite.setOnClickListener {
                listener.onFavorite(apartment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_apartment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val apartment = apartments.get(position)
        with(holder) {
            setListener(apartment)
        }
    }

    override fun getItemCount(): Int = apartments.size
}
