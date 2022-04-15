package com.rdpp.komorebi

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
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

    }
}