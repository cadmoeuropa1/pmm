package com.alc.a1_ejerciciologinadil_laamiri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import com.alc.a1_ejerciciologinadil_laamiri.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    //val dice que reservara esa direccion de memoria
    //listOf indica que esta lista es inmutable

    val usuarios: List<Usuario> = listOf(
            Usuario("adil","000")
            ,Usuario("carba","111")
            ,Usuario("javi","222")
            ,Usuario("manuel","333"))


    //para utilizar Binding debo ir a gradle Module y.....
    /*
        buildFeatures{
            viewBinding true
        }
     */
    private  lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //infla todas las vistas que agrupa esta Activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        //le indica la ruta donde comparte las vistas de esta Activity
        setContentView(binding.root)

        binding.btnValidar.setOnClickListener {


            validar()

        }

    }

    fun validar():Unit{

        var pos =0
        var encontrado= false
        val intent = Intent(this, ListaProductos::class.java)

        var id: String = binding.txtLogin.text.toString()
        var pass: String =binding.txtPass.text.toString()

        while (pos < usuarios.size && !encontrado){

            var usuario:String = usuarios.get(pos).login
            var contra:String = usuarios.get(pos).pass

            if ( usuario.equals(id) ){
                if (contra.equals(pass)){

                    Snackbar.make(binding.root,"Validado Correctamente",Snackbar.LENGTH_SHORT).show()
                    startActivity(intent)
                    encontrado=true

                }//fin if contraseña

            }//fin if de login
        pos++
        }//fin de bucle while

        if(!encontrado){

            Snackbar.make(binding.root,"No se pudo validar",Snackbar.LENGTH_SHORT).show()

        }
    }

}