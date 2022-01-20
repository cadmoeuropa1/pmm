package com.rdpp.ej2basesdedatos

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.rdpp.ej2basesdedatos.database.LawFirmDAO
import com.rdpp.ej2basesdedatos.databinding.ActivityMainBinding
import com.rdpp.ej2basesdedatos.dataclasses.Case
import com.rdpp.ej2basesdedatos.dataclasses.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: LawFirmDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        database = LawFirmDAO(this)
        setContentView(binding.root)
        binding.frameLogin.visibility = View.INVISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            binding.frameImage.visibility = View.INVISIBLE
            binding.txtName.visibility = View.INVISIBLE
            binding.frameLogin.visibility = View.VISIBLE
        }, 3000)

        binding.btnLogin.setOnClickListener {
            validateUser()
        }

    }

    private fun validateUser() {
        val login: String = binding.textUser.text.toString()
        val password: String = binding.textPassword.text.toString()
        if (login == "" || password == "") {
            Snackbar.make(
                binding.root,
                getString(R.string.login_error_empty_fields),
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            val user = database.getUser(login, password)
            if (user != null) {
                if (user.type == "L") {
                    Snackbar.make(binding.root, "User logged", Snackbar.LENGTH_SHORT).show()
                    val intent = Intent(this, MainScreen_Lawyer::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)
                } else if (user.type == "S") {
                    Snackbar.make(binding.root, "User logged", Snackbar.LENGTH_SHORT).show()
                    val intent = Intent(this, MainScreen_Admin::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)
                }
            } else {
                Snackbar.make(binding.root, (R.string.login_error_not_found), Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
