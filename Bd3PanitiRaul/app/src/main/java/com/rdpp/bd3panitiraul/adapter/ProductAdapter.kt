package com.rdpp.bd3panitiraul.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rdpp.bd3panitiraul.R
import com.rdpp.bd3panitiraul.databinding.ProductCardLayoutBinding
import com.rdpp.bd3panitiraul.dataclass.Product
import com.rdpp.bd3panitiraul.listener.EventListener

class ProductAdapter(
    private var products: MutableList<Product>, private var listener: EventListener,
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ProductCardLayoutBinding.bind(view)
        fun setListener(product: Product) {
            binding.btnDeleteProduct.setOnClickListener {
                listener.deleteProduct(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.product_card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        with(holder) {
            setListener(product)
            binding.txtProdName.text = product.name
            Glide.with(binding.root).load(product.image).centerCrop().diskCacheStrategy(
                DiskCacheStrategy.ALL
            ).into(binding.imgProduct)
        }
    }

    override fun getItemCount(): Int = products.size

    fun setProducts(productsR: MutableList<Product>?) {
        if (productsR != null) {
            this.products = productsR
        }
    }

}