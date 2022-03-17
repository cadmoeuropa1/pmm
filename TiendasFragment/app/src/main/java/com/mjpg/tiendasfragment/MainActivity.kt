package com.mjpg.tiendasfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mjpg.tiendasfragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (binding.fragContenedor != null) {
            val fragment = ConsultaFragment()
            fragmentTransaction.add(R.id.frag_contenedor, fragment)

        } else {
            val fragmento2 = EditarFragment()
            fragmentTransaction.add(R.id.fragmentdetalle, fragmento2)
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

}


