package com.rdpp.fragmentPaniti.ui

import FragmentPaniti.R
import FragmentPaniti.databinding.ActivityMainScreenBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainScreen : AppCompatActivity() {
    private lateinit var binding: ActivityMainScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}