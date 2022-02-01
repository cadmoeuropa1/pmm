package com.rdpp.bd3panitiraul.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rdpp.bd3panitiraul.R
import com.rdpp.bd3panitiraul.databinding.ShoppingListCardLayoutBinding
import com.rdpp.bd3panitiraul.dataclass.ShoppingList
import com.rdpp.bd3panitiraul.listener.ListEventListener

class ListAdapter(
    private var lists: MutableList<ShoppingList>,
    private var listener: ListEventListener
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ShoppingListCardLayoutBinding.bind(view)
        fun setListener(position: Int, shoppingList: ShoppingList) {
            binding.btnDelete.setOnClickListener {
                listener.deleteList(shoppingList)
                notifyItemRemoved(position)
            }
            binding.btnEdit.setOnClickListener {
                listener.editList(shoppingList)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.shopping_list_card_layout, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shopList = this.lists[position]
        with(holder) {
            setListener(position, shopList)
            binding.txtListName.text = shopList.name
        }
    }

    override fun getItemCount() = lists.size

    fun setLists(lists: MutableList<ShoppingList>?) {
        if (lists != null) {
            this.lists = lists
        }
    }
}

