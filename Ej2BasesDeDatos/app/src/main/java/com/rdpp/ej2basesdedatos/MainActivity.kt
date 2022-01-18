package com.rdpp.ej2basesdedatos

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rdpp.ej2basesdedatos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handler(Looper.getMainLooper()).postDelayed({
        //     LoadScreen().execute()
        // }, 3000)
    }

    /* private inner class LoadScreen() : AsyncTask<Void, Void, Void>() {
         override fun doInBackground(vararg p0: Void?): Void? {
             Glide.with(binding.root).load(getString(R.string.image_URL))
                 .centerCrop()
                 .diskCacheStrategy(DiskCacheStrategy.ALL)
                 .into(binding.imgLogo)

             return null
         }

         override fun onPostExecute(result: Void?) {
             super.onPostExecute(result)
             val intent = Intent(this, )
             TODO("Not yet implemented")
         }

     }*/

}