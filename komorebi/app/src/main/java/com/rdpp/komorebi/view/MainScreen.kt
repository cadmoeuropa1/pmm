package com.rdpp.komorebi.view

import android.content.Intent
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
                    startActivity(Intent(this, NewsActivity::class.java))
                    true
                }
                R.id.btn_Chat -> {
                    startActivity(Intent(this, ChatsActivity::class.java))
                    true
                }
                R.id.btn_Forum -> {
                    startActivity(Intent(this, ForumActivity::class.java))
                    true
                }
                R.id.btn_Workshop -> {
                    startActivity(Intent(this, WorkshopActivity::class.java))
                    true
                }
                R.id.menu_information -> {
                    startActivity(Intent(this, InformationActivity::class.java))
                    true
                }
                R.id.menu_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> {
                    false
                }
            }
        }

    }
}