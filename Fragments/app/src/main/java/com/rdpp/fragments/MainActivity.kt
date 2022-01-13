    package com.rdpp.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rdpp.fragments.databinding.ActivityMainBinding

    class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(R.id.containerMain != null) {
            val fragment = ConsultFragment()
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.containerMain, fragment)
            fragmentTransaction.commit()
        }else{
            val fragment = EditFragment()
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.editContainer, fragment)
            fragmentTransaction.commit()
        }
    }
}