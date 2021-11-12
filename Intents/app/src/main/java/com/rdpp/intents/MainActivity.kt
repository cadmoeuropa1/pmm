package com.rdpp.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.rdpp.intents.databinding.ActivityMainBinding

/*
* En este ejercicio veremos como pasar información de una activity a otra por medio de la clase
* Intent. Necesario para el ejercicio 2 del bloque 1 de Ejercicios de Kotlin
*
*
    */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val NAME = "name"
        const val USER = "user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSend.setOnClickListener(escucharBoton)
    }

    var escucharBoton: View.OnClickListener = View.OnClickListener {
        var textToSend = binding.textToSend2.text.toString()
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(NAME, textToSend)
        val user = User("Maria", "Juarez")
        intent.putExtra(USER, user)
        activityResult.launch(intent)

    }
    val activityResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val datos: Intent? = result.data
            val resultado = datos?.getFloatExtra("valores", 0.0f)
            binding.tvResult.text =
                " Condiciones aceptadas.\n Gracias por dar su valoración con $resultado estrellas"
        } else {
            binding.tvResult.text = "Se ha cancelado la valoración"
        }
    }

}