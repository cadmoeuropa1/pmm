package com.rdpp.bd3panitiraul.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.rdpp.bd3panitiraul.R
import com.rdpp.bd3panitiraul.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentTransaction.add(R.id.containerMain, CategoriesFragment())

        binding.bnwMenu.setOnItemSelectedListener {
            if (binding.bnwMenu.selectedItemId == R.id.btn_categories) {
                val fragment = CategoriesFragment()
                setFragment(fragment, fragmentManager)
            }
            if (binding.bnwMenu.selectedItemId == R.id.btn_products) {
                val fragment = ProductsFragment()
                setFragment(fragment, fragmentManager)
            }
            if (binding.bnwMenu.selectedItemId == R.id.btn_shopping_lists) {
                val fragment = ShoppingListFragment()
                setFragment(fragment, fragmentManager)
            }
            true
        }
    }

    private fun setFragment(fragment: Fragment, fragmentManager: FragmentManager) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.containerMain, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }
}