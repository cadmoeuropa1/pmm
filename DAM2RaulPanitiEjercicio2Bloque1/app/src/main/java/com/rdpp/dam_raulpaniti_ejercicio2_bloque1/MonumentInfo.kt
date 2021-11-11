package com.rdpp.dam_raulpaniti_ejercicio2_bloque1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mse.dam2_raulpaniti_ejercicio2_bloque1.databinding.ActivityMonumentInfoBinding


class MonumentInfo : AppCompatActivity() {
    private lateinit var binding: ActivityMonumentInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMonumentInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var monument = intent.getParcelableExtra<Monument>("monument")
    }

}