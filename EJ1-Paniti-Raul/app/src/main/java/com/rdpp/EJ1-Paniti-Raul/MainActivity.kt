package com.rdpp.`EJ1-Paniti-Raul`

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.rdpp.dam2_raul_paniti_ejercicio1_bloque1.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {
    //Creamos los usuarios antes de crear la actividad
    //Junto con el binding de la actividad

    val users: List<User> = listOf(
        User("pepe", "000"),
        User("juan", "001")
    )

    //Para utilizar este binding debemos escribir
    //buildFeatures{
    //        viewBinding true
    //    }
    //En la parte del módulo de nuestro proyecto, en Gradle

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val intent = Intent(this, ProductList::class.java)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            //Validaremos el usuario por medio del método Validar()
            var user = Validate()
            if (user !=-1) {
                Snackbar.make(binding.root, "Usuario Validado", Snackbar.LENGTH_SHORT)
                    .show()
                startActivity(intent)
            } else {
                Snackbar.make(binding.root,
                    "Validación errónea, intentélo de nuevo",
                    Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun Validate(): Int {
        var user = 0
        try {
            var userLog = User(binding.txtLogin.text.toString(), binding.txtPass.text.toString())
            user = users.indexOf(userLog)
        } catch (exception: IOException) {
            Snackbar.make(binding.root, "Formato incorrecto", Snackbar.LENGTH_SHORT)
                .show()
        }
        return user
    }

}