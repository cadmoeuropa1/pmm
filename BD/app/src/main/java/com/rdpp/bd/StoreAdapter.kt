package com.rdpp.bd

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rdpp.bd.databinding.ItemStoreBinding

class StoreAdapter(
    private var stores: MutableList<Store>,
    private var listener: EventListener,
) : RecyclerView.Adapter<StoreAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemStoreBinding.bind(view)
        fun setListener(store: Store) {
            with(binding.root) {
                setOnClickListener { listener.edit(store.id) }
                setOnLongClickListener {
                    listener.deleteStore(store.id)
                    true
                }

            }
            binding.cbFavorite.setOnClickListener {
                listener.onFavorite(store)
            }
        }
    }

    override fun getItemCount(): Int = stores.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_store, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val store = stores.get(position)
        with(holder) {
            setListener(store)
            binding.tvName.text = store.name
            binding.cbFavorite.isChecked = store.isFavorite.equals(0)
        }
    }

    fun add(store: Store) {
        stores.add(store)
        notifyDataSetChanged()

    }

    fun setStores(stores: MutableList<Store>) {
        this.stores = stores
        notifyDataSetChanged()
    }

    fun update(store: Store) {
        val pos = stores.indexOf(store)
        if (pos != -1) {
            stores.set(pos, store)
            notifyItemChanged(pos)
        } else {

        }
    }

    fun delete(id: Long) {
        val store = Store(id, "", 0)
        val pos = stores.indexOf(store)
        if (pos != -1) {
            stores.removeAt(pos)
            notifyItemRemoved(pos)
        }

    }
}