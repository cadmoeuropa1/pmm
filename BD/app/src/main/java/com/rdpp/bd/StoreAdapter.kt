package com.rdpp.bd

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rdpp.bd.databinding.ItemStoreBinding

class StoreAdapter(
    private var shops: MutableList<Store>,
    private var listener: EventListener,
) : RecyclerView.Adapter<StoreAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemStoreBinding.bind(view)
        fun setListener(shop: Store) {
            with(binding.root) {
                setOnClickListener { listener.edit(shop.id) }
                setOnLongClickListener { true }

            }
        }
    }

    override fun getItemCount(): Int = shops.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_store, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shop = shops.get(position)
        with(holder) {
            setListener(shop)
            binding.tvName.text = shop.name
            binding.cbFavorite.isChecked = shop.isFavorite
        }
    }


}