package com.rdpp.panitiraul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rdpp.panitiraul.databinding.ActivityInformacionBinding

class Informacion : AppCompatActivity(), EventosListener {
    private lateinit var binding: ActivityInformacionBinding
    private lateinit var adaptadorEspecialidad: AdaptadorEspecialidad
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        linearLayoutManager = LinearLayoutManager(this)
        adaptadorEspecialidad = AdaptadorEspecialidad(getEspecialidades(), this)
        super.onCreate(savedInstanceState)
        binding = ActivityInformacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerview.adapter = adaptadorEspecialidad
        binding.recyclerview.layoutManager = linearLayoutManager
    }

    private fun getEspecialidades(): List<Especialidad> {
        val especialidades: List<Especialidad> = listOf(
            Especialidad(1, "Trauma", 3),
            Especialidad(2, "Oncología", 2),
            Especialidad(3, "Geriatría", 2),
            Especialidad(4, "Cardiología", 3)
        )
        return especialidades
    }

    override fun onLongClickListener(especialidad: Especialidad) {
        val intent = Intent(this, Registro::class.java)
        intent.putExtra("codigoEspecialidad", especialidad.codigoEspecialidad)
        intent.putExtra("Annos", especialidad.annos)
        setResult(2, intent)
        finish()
    }
}