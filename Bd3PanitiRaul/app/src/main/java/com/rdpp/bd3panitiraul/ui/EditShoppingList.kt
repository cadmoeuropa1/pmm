package com.rdpp.bd3panitiraul.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.rdpp.bd3panitiraul.adapter.ProductListAdapter
import com.rdpp.bd3panitiraul.databinding.ActivityEditShoppingListBinding

class EditShoppingList : AppCompatActivity() {
    private lateinit var binding: ActivityEditShoppingListBinding
    private lateinit var adapterR: ProductListAdapter
    private lateinit var layout: GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}