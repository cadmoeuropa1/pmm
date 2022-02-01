package com.rdpp.bd3panitiraul.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rdpp.bd3panitiraul.databinding.ActivityEditShoppingListBinding

class EditShoppingList : AppCompatActivity() {
    private lateinit var binding: ActivityEditShoppingListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}