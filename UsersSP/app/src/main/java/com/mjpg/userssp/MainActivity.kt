package com.mjpg.userssp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLog
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mjpg.userssp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), EventosListener {


    private lateinit var binding: ActivityMainBinding
    private lateinit var usuarioAdaptador: UsuarioAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        usuarioAdaptador = UsuarioAdapter(getUsuario(), this)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = usuarioAdaptador
        binding.recyclerview.layoutManager = linearLayoutManager

    }

    private fun getUsuario(): MutableList<Usuario> {
        val usuarios = mutableListOf<Usuario>()
        val usuario1 = Usuario(1,
            "Pepe",
            "Juanez",
            "https://cope-cdnmed.agilecontent.com/resources/jpg/4/0/1601032304304.jpg")
        val usuario2 = Usuario(1,
            "Maria",
            "Sanchez",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/1/10/SASSOFERRATO_-_Virgen_rezando_%28National_Gallery%2C_Londres%2C_1640-50%29.jpg/1200px-SASSOFERRATO_-_Virgen_rezando_%28National_Gallery%2C_Londres%2C_1640-50%29.jpg")
        val usuario3 = Usuario(1,
            "Jorge",
            "Casado",
            "https://yt3.ggpht.com/ytc/AKedOLTjq90N3KKoDNEEDs-j1bs7dDgzjmqULsi6fHUqFQ=s900-c-k-c0x00ffffff-no-rj")
        val usuario4 = Usuario(1,
            "Ivan",
            "Martinez",
            "https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/styles/480/public/media/image/2020/09/magnifico-ivan-2063095.jpg?itok=nSdJqr2i")
        usuarios.add(usuario1)
        usuarios.add(usuario2)
        usuarios.add(usuario3)
        usuarios.add(usuario4)
        return usuarios
    }

    override fun onClickListener(usuario: Usuario, position: Int) {
        Toast.makeText(this,
            "El usuario es " + usuario.personalizado() + " posicion " + position.toString(),
            Toast.LENGTH_SHORT).show()
    }
}