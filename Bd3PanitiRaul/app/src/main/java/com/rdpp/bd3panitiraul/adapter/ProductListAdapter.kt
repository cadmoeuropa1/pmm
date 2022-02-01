package com.rdpp.bd3panitiraul.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rdpp.bd3panitiraul.R
import com.rdpp.bd3panitiraul.database.ShoppingListDAO
import com.rdpp.bd3panitiraul.databinding.ProductListLayoutBinding
import com.rdpp.bd3panitiraul.dataclass.Product_List

class ProductListAdapter(
    private var products_list: MutableList<Product_List>
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private lateinit var context: Context
    private lateinit var database: ShoppingListDAO
    var quantity = 0

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ProductListLayoutBinding.bind(view)
        fun setListener(productList: Product_List) {
            binding.btnMinus.setOnClickListener {
                decreaseQuantity()
            }
            binding.btnPlus.setOnClickListener {
                increaseQuantity()
            }
        }
    }

    private fun increaseQuantity() {
        this.quantity += 1
    }

    private fun decreaseQuantity() {
        if (quantity == 0) {

        } else {
            quantity -= 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        database = ShoppingListDAO(context)
        val view = LayoutInflater.from(context).inflate(R.layout.product_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productList = this.products_list[position]
        with(holder) {
            setListener(productList)
            binding.txtProductName.text = database.getProductNameFromList(productList.list_Id)
            binding.txtProductCategory.text = database.getProductCategory(productList.prod_Id)
        }
    }

    override fun getItemCount() = products_list.size

}