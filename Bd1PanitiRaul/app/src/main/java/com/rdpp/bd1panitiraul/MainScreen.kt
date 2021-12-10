package com.rdpp.bd1panitiraul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rdpp.bd1panitiraul.databinding.ActivityMainScreenBinding

class MainScreen : AppCompatActivity() {
    lateinit var binding: ActivityMainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}