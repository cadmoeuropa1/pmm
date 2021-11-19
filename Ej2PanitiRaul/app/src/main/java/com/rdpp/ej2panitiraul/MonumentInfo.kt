package com.rdpp.ej2panitiraul

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rdpp.ej2panitiraul.databinding.ActivityMonumentInfoBinding

class MonumentInfo : AppCompatActivity() {
    private lateinit var binding: ActivityMonumentInfoBinding

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMonumentInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val monument = intent.getParcelableExtra<Monument>("monument")

        Glide.with(this).load(monument?.image)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imgMonument)
        binding.txtLocation.text = monument?.location
        binding.txtPhone.text = monument?.phoneNumber.toString()
        binding.txtMail.text = monument?.mail
        binding.txtDescription.text = monument?.description
        binding.txtName.text = monument?.name

        if(monument?.phoneNumber.toString() == ""){
            binding.txtPhone.text = getString(R.string.phone_number_info_error)
        }
    }
}