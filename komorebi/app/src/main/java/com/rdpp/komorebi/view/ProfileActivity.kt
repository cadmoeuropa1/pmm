package com.rdpp.komorebi.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rdpp.komorebi.R
import com.rdpp.komorebi.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Glide.with(binding.root)
            .load("https://www.xtrafondos.com/descargar.php?id=5846&resolucion=3840x2160")
            .circleCrop().diskCacheStrategy(
            DiskCacheStrategy.ALL
        ).into(binding.imgProfile)
    }
}