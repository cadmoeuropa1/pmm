package com.rdpp.fragmentPaniti.ui

import FragmentPaniti.R
import FragmentPaniti.databinding.ActivityMainBinding
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.rdpp.fragmentPaniti.database.ProgrammersDAO
import com.rdpp.fragmentPaniti.dataclass.Event
import com.rdpp.fragmentPaniti.dataclass.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: ProgrammersDAO

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = ProgrammersDAO(this)
        binding.frameLogin.visibility = View.INVISIBLE
        binding.btnLogin.visibility = View.INVISIBLE

        Glide.with(binding.root)
            .load(getString(R.string.url_image))
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imgLogo)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                binding.frameLogin.visibility = View.VISIBLE
                binding.frameLogo.visibility = View.INVISIBLE
                binding.btnLogin.visibility = View.VISIBLE
            }, 3000
        )
        loadUsers()
        loadEvents()
        binding.btnLogin.setOnClickListener {
            validateUser()
        }

    }

    private fun loadEvents() {
        val event1 = Event(
            date = "2022-02-22",
            title = "Evento para Principiantes",
            description = "Evento enfocado a principiantes, para construir una base enfocada a la programación orientada a objetos"
        )
        val event2 = Event(
            date = "2022-02-27",
            title = "Evento para usuarios Medios",
            description = "Evento enfocado a personas con conocimientos medios de programación Orientada a Objetos"
        )
        val event3 = Event(
            date = "2022-03-01",
            title = "Evento para para Expertos",
            description = "Evento enfocado en los usuarios expertos, desarrolladores, etc..."
        )
        database.addEvent(event1)
        database.addEvent(event2)
        database.addEvent(event3)
    }

    private fun loadUsers() {
        val loginArray = resources.getStringArray(R.array.logins)
        val passArray = resources.getStringArray(R.array.passwords)
        val typesArray = resources.getStringArray(R.array.types)
        for (u in loginArray.indices) {
            val user = User(login = loginArray[u], pass = passArray[u], type = typesArray[u])
            database.addUser(user)
        }

        /*
        private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
            val formatter = SimpleDateFormat(format, locale)
            return formatter.format(this)
        }

        private fun getCurrentDateTime(): Date {
            return Calendar.getInstance().time
        }
        */
    }
    private fun validateUser() {
        val login: String = binding.txtUser.text.toString()
        val password: String = binding.txtPass.text.toString()
        if (login == "" || password == "") {
            Snackbar.make(
                binding.root,
                getString(R.string.login_error_empty_fields),
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            val user = database.getUser(login, password)
            if (user != null) {
                Snackbar.make(binding.root, "User logged", Snackbar.LENGTH_SHORT).show()
                val intent = Intent(this, MainScreen::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            } else {
                Snackbar.make(binding.root, (R.string.login_error_not_found), Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }
}