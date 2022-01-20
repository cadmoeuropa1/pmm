package com.rdpp.ej2basesdedatos

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
        Glide.with(binding.root)
            .load(getString(R.string.image_URL))
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imgLogo)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                binding.frameImage.visibility = View.INVISIBLE
                binding.txtName.visibility = View.INVISIBLE
                binding.frameLogin.visibility = View.VISIBLE
            }, 3000
        )
        binding.btnLogin.setOnClickListener {
            validateUser()
        }
        firstInserts()

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
                }
            } else {
                Snackbar.make(binding.root, (R.string.login_error_not_found), Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun firstInserts() {
        val insertUser1: User = User("01", "PedroPa", "pedro", "123", "L")
        val insertUser2: User = User("02", "Adil", "adil", "123", "S")

        val insertCase1: Case = Case(
            "Pokemon",
            "09/2012",
            "Corruption case in Galicia",
            "02"
        )
        val insertCase2: Case =
            Case("Construction", "02/1994", "Construction corruption case in Burgos", "01")



        database.addUser(insertUser1)
        database.addUser(insertUser2)
        database.addCase(insertCase1)
        database.addCase(insertCase2)
    }
}


