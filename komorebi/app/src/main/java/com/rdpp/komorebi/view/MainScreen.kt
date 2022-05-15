package com.rdpp.komorebi.view

import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rdpp.komorebi.R
import com.rdpp.komorebi.databinding.ActivityMainScreenBinding

class MainScreen : AppCompatActivity() {
    private lateinit var binding: ActivityMainScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.topAppBar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.btn_News -> {
                    Toast.makeText(this, "Prueba ${it.title.toString()}", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.btn_Chat -> {
                    Toast.makeText(this, "Prueba ${it.title.toString()}", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.btn_Forum -> {
                    Toast.makeText(this, "Prueba ${it.title.toString()}", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.btn_Workshop -> {
                    Toast.makeText(this, "Prueba ${it.title.toString()}", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.menu_information -> {
                    Toast.makeText(this, "Prueba ${it.title.toString()}", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.menu_profile -> {
                    Toast.makeText(this, "Prueba ${it.title.toString()}", Toast.LENGTH_LONG).show()
                    true
                }
                else -> {
                    false
                }
            }
        }

    }
}