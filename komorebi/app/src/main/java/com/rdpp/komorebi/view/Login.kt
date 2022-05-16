package com.rdpp.komorebi.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rdpp.komorebi.R
import com.rdpp.komorebi.database.KomorebiDAO
import com.rdpp.komorebi.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: KomorebiDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        database = KomorebiDAO(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database.insertExamples()

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
        }
        binding.imgLogo.setOnClickListener {
            Toast.makeText(this, "Llamando al servicio de emergencias 112", Toast.LENGTH_LONG)
                .show()
        }
    }
}