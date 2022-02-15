package com.rdpp.panitiraul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.rdpp.panitiraul.databinding.ActivityRegistroBinding
import java.lang.NumberFormatException


class Registro : AppCompatActivity(), EventosListener {
    private lateinit var binding: ActivityRegistroBinding
    private val listaCandidatos = mutableListOf<Candidato>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val codigoDevuelto: Int
        val annoDevuelto: Int
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnFinalizarApp.setOnClickListener {
            System.exit(0)
        }
        binding.btnCancelar.setOnClickListener {
            limpiar()
        }
        binding.btnRegistrar.setOnClickListener {
            try {
                val dni = binding.etxtDni.text.toString()
                val nombre = binding.etxtNombreApellidos.text.toString()
                val anno = Integer.parseInt(binding.etxtAnnoTitulacion.text.toString())
                val codigo = Integer.parseInt(binding.etxtCodigo.text.toString())
                if (dni == "" || nombre == "" || anno == 0 || codigo == 0) {
                    Snackbar.make(binding.root,
                        "Ninguno de los campos puede estar vacío",
                        Snackbar.LENGTH_SHORT).show()
                } else {
                    //comprobarYRegistrar(dni, nombre, anno, codigo)
                }
            } catch (exception: NumberFormatException) {
                exception.stackTrace
            }

        }
        binding.btnInformacion.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            startActivityForResult(intent, 2)

        }
    }

    private fun limpiar() {
        binding.etxtDni.setText("")
        binding.etxtCodigo.setText("")
        binding.etxtAnnoTitulacion.setText("")
        binding.etxtNombreApellidos.setText("")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                val code = data?.getIntExtra("codigoEspecialidad", 0)
                if (code != null) {
                    binding.etxtCodigo.setText(code)
                }
                val year = data?.getIntExtra("Annos", 0)
            }
        }
    }
}
