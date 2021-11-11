package com.rdpp.ej2panitiraul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rdpp.ej2panitiraul.databinding.ActivityMonumentInfoBinding

class MonumentInfo : AppCompatActivity() {
    private lateinit var binding : ActivityMonumentInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMonumentInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var monument =intent.getParcelableExtra<Monument>("monument")
    }
}