package com.rdpp.bd3panitiraul.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.rdpp.bd3panitiraul.R
import com.rdpp.bd3panitiraul.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fragmentManager = supportFragmentManager

    private lateinit var fragmentTransaction: FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.containerMain, CategoriesFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
        binding.bnwMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.btn_categories -> {
                    val fragment = CategoriesFragment()
                    setFragment(fragment)
                }

                R.id.btn_products -> {
                    val fragment = ProductsFragment()
                    setFragment(fragment)
                }
                else -> {
                    val fragment = ShoppingListFragment()
                    setFragment(fragment)
                }
            }
            true
        }
    }

    private fun setFragment(fragment: Fragment) {
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.containerMain, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }


}