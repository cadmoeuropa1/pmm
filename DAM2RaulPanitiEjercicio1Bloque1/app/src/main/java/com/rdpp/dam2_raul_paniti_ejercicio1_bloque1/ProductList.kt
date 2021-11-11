package com.rdpp.dam2_raul_paniti_ejercicio1_bloque1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.google.android.material.snackbar.Snackbar
import com.rdpp.dam2_raul_paniti_ejercicio1_bloque1.databinding.ActivityProductListBinding
import java.io.IOException
import java.text.ParseException

class ProductList : AppCompatActivity() {
    //Creo una lista de Productos, antes de que se cree la Activity
    val products: List<Product> = listOf(
        Product(0, "Television 4K",
            "https://thumb.pccomponentes.com/w-530-530/articles/24/247604/43-3.jpg",
            "https://www.pccomponentes.com/xiaomi-mi-tv-4s-43-led-ultrahd-4k"),
        Product(1,
            "Consola PS4",
            "https://thumb.pccomponentes.com/w-530-530/articles/18/181577/1.jpg",
            "https://www.pccomponentes.com/sony-playstation-4-slim-chasis-f-500gb-reacondicionado"),
        Product(2,
            "Teclado mecánico básico",
            "https://thumb.pccomponentes.com/w-530-530/articles/42/422651/1532-mars-gaming-mkmini-teclado-mecanico-switch-outemu-rojo.jpg",
            "https://www.pccomponentes.com/mars-gaming-mkmini-teclado-mecanico-switch-outemu-rojo")
    )

    //Hago el binding de esta activity
    private lateinit var binding: ActivityProductListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            searchProduct()
            Glide.with(binding.root).load(products.get(searchProduct()).image).into(binding.imgProduct)
            binding.txtProduct.text = products.get(searchProduct()).name
        }
    }

    private fun searchProduct(): Int {
        var pos:Int = 0
        var codeFound: Int= 0
        var found:Boolean = false
        try {
            var code: Int = binding.etxtCode.text.toString().toInt()
            while(pos<products.size && found==false){
                if(code == products.get(pos).code){
                    codeFound = products.get(pos).code
                    found = true
                }
                pos++
            }
        } catch (exception: IOException) {
            Snackbar.make(binding.root, "Código de producto no válido", Snackbar.LENGTH_SHORT)
                .show()
        }
        return codeFound
    }
}