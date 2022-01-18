package com.rdpp.ej2basesdedatos

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rdpp.ej2basesdedatos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.frameLogin.visibility = View.INVISIBLE
        Glide.with(binding.root).load(getString(R.string.image_URL))
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imgLogo)

         Handler(Looper.getMainLooper()).postDelayed({
             binding.frameImage.visibility = View.INVISIBLE
             binding.frameLogin.visibility = View.VISIBLE
             binding.txtName.text = ""
         }, 3000)

        binding.btnLogin.setOnClickListener {
            validateUser()
        }
    }

    private inner class LoadScreen() : AsyncTask<Void, Void, Void>() {
         override fun doInBackground(vararg p0: Void?): Void? {
             Glide.with(binding.root).load(getString(R.string.image_URL))
                 .centerCrop()
                 .diskCacheStrategy(DiskCacheStrategy.ALL)
                 .into(binding.imgLogo)

             return null
         }

         override fun onPostExecute(result: Void?) {
             super.onPostExecute(result)
             binding.frameImage.visibility = View.INVISIBLE
             binding.frameLogin.visibility = View.VISIBLE
         }

     }


}