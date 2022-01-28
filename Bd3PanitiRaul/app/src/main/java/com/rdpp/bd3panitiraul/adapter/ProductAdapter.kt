package com.rdpp.bd3panitiraul.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rdpp.bd3panitiraul.databinding.ProductCardLayoutBinding
import com.rdpp.bd3panitiraul.dataclass.Product
import com.rdpp.bd3panitiraul.listener.EventListener

class ProductAdapter(
    private var products: MutableList<Product>, private var listener: EventListener,
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ProductCardLayoutBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = products.size

    fun setProducts(productsR: MutableList<Product>?) {
        if (productsR != null) {
            this.products = productsR
        }
    }
}