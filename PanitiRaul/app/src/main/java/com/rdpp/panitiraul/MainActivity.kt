package com.rdpp.panitiraul

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.rdpp.panitiraul.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: EmpresaDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        database = EmpresaDAO(this)
        cargarUsuarios()
        cargarCategorias()
        cargarProductos()
        setContentView(binding.root)
        binding.frameLogin.visibility = View.INVISIBLE
        Glide.with(binding.root)
            .load("https://www.america-retail.com/static//2020/08/tienda-ropa.jpg")
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imgLogo)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                binding.frameLogin.visibility = View.VISIBLE
                binding.frameLogo.visibility = View.INVISIBLE
            }, 3000
        )
        binding.btnLogin.setOnClickListener {
            validateUser()
        }

    }

    fun cargarUsuarios() {
        val usuarios = ArrayList<Usuario>()
        val usuario1 = Usuario(login = "paniti", contra = "1234", nombre = "Raúl")
        val usuario2 = Usuario(login = "pepe", contra = "123", nombre = "Pepito")
        usuarios.add(usuario1)
        usuarios.add(usuario2)
        database.annadirUsuarios(usuarios)
    }

    fun cargarCategorias() {
        val categorias = ArrayList<Categoria>()
        val categoria1 = Categoria(nombre = "Pantalones")
        val categoria2 = Categoria(nombre = "Camisetas")
        val categoria3 = Categoria(nombre = "Sudaderas")
        categorias.add(categoria1)
        categorias.add(categoria2)
        categorias.add(categoria3)
        database.annadirCategorias(categorias)
    }

    fun cargarProductos() {
        val productos = ArrayList<Producto>()
        val producto1 = Producto(
            cat_Id = 1, nombre = "Pantalones pitillos", pvp = 14, imagen = getString(
                R.string.imagenProducto1
            ), color = "Marron", talla = "M"
        )
        val producto2 = Producto(
            cat_Id = 2, nombre = "Camiseta básica", pvp = 9, imagen = getString(
                R.string.imagenProducto2
            ), color = "Marron", talla = "M"
        )
        val producto3 = Producto(
            cat_Id = 2, nombre = "Camiseta dibujo", pvp = 12, imagen = getString(
                R.string.imagenProducto3
            ), color = "Marron", talla = "M"
        )
        val producto4 = Producto(
            cat_Id = 3, nombre = "Sudadera negra con dibujo", pvp = 26, imagen = getString(
                R.string.imagenProducto4
            ), color = "Marron", talla = "M"
        )
        productos.add(producto1)
        productos.add(producto2)
        productos.add(producto3)
        productos.add(producto4)
        database.annadirProductos(productos)
    }

    private fun validateUser() {
        val login: String = binding.etLogin.text.toString()
        val contra: String = binding.etContra.text.toString()
        if (login == "" || contra == "") {
            Snackbar.make(
                binding.root,
                "Los campos no pueden estar vacíos",
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            val usuario = database.getUsuario(login, contra)
            if (usuario != null) {
                Snackbar.make(binding.root, "Usuario conectado", Snackbar.LENGTH_SHORT).show()
                val intent = Intent(this, ContainerActivity::class.java)
                intent.putExtra("usuario", usuario)
                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.root,
                    "Usuario no encontrado, intentelo de nuevo",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
        }
    }
}