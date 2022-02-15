package com.rdpp.panitiraul

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.rdpp.panitiraul.databinding.ActivityContainerBinding

class ContainerActivity : AppCompatActivity() {
    private lateinit var database: EmpresaDAO
    private lateinit var binding: ActivityContainerBinding
    private lateinit var fragmentTransaction: FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContainerBinding.inflate(layoutInflater)
        database = EmpresaDAO(this)
        setContentView(binding.root)
        val fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()

        if (binding.fragmentContainer != null) {
            lanzarMovil(savedInstanceState)
        } else {
            lanzarTablet(savedInstanceState)
        }
    }

    private fun lanzarTablet(savedInstanceState: Bundle?) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = FragmentCategorias()
        val fragment2 = FragmentProductos()
        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.fragmentCategorias, fragment)
            fragmentTransaction.add(R.id.fragmentdetalle, fragment2)
        } else {
            fragmentTransaction.replace(R.id.fragmentCategorias, fragment)
            fragmentTransaction.replace(R.id.fragmentdetalle, fragment2)

        }
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun lanzarMovil(savedInstanceState: Bundle?) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = FragmentCategorias()
        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.fragmentContainer, fragment)
        } else {
            fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        }
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}