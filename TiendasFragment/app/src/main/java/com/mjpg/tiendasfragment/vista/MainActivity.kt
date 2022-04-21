package com.mjpg.tiendasfragment.vista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mjpg.tiendasfragment.R
import com.mjpg.tiendasfragment.databinding.ActivityMainBinding
import com.mjpg.tiendasfragment.vistamodelo.VistaModelo
import com.mjpg.tiendasfragment.vistamodelo.VistaModeloFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModelo: VistaModelo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModeloFactory=VistaModeloFactory(0)
        viewModelo = ViewModelProvider(this,viewModeloFactory).get(VistaModelo::class.java)

        if (binding.fragContenedor != null)
            lanzarSmartPhone(savedInstanceState)
        else
            lanzarTablet(savedInstanceState)
    }

    private fun lanzarSmartPhone(savedInstanceState: Bundle?) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = ConsultaFragment()
        if (savedInstanceState == null)
            fragmentTransaction.add(R.id.frag_contenedor, fragment)
        else
            fragmentTransaction.replace(R.id.frag_contenedor, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun lanzarTablet(savedInstanceState: Bundle?) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = EditarFragment()
        val fragment2= ConsultaFragment()
        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.fragmentLista, fragment2)
            fragmentTransaction.add(R.id.fragmentdetalle, fragment)
        }
        else {
            fragmentTransaction.replace(R.id.fragmentLista, fragment2)
            fragmentTransaction.replace(R.id.fragmentdetalle, fragment)
        }
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun anadir() {
        val fragmentManager = supportFragmentManager;
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (binding.fragContenedor != null) {
            val fragment = EditarFragment()
            fragmentTransaction.replace(R.id.frag_contenedor, fragment)

        } else {
            val fragmento2 = EditarFragment()
            fragmentTransaction.replace(R.id.fragmentdetalle, fragmento2)
        }
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    public fun editar(id:Long){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment= EditarFragment()
        var args=Bundle()
        args.putLong("id",id)
        fragment.arguments=args
        if(binding.fragmentdetalle==null)
            fragmentTransaction.replace(R.id.frag_contenedor,fragment)
        else
            fragmentTransaction.replace(R.id.fragmentdetalle,fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}


