package com.rdpp.bd3panitiraul.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rdpp.bd3panitiraul.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}