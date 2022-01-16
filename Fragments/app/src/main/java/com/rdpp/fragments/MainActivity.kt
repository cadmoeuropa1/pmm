package com.rdpp.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rdpp.fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (binding.containerMain != null) {
            val fragment = ConsultFragment()
            fragmentTransaction.add(R.id.containerMain, fragment)

        } else {
            val fragment = EditFragment()
            fragmentTransaction.add(R.id.editContainer, fragment)

        }
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun add() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (binding.containerMain != null) {
            val fragment = EditFragment()
            fragmentTransaction.replace(R.id.containerMain, fragment)

        } else {
            val fragment = EditFragment()
            fragmentTransaction.replace(R.id.editContainer, fragment)

        }
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
