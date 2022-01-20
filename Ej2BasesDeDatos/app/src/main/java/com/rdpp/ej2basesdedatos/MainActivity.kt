package com.rdpp.ej2basesdedatos

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.rdpp.ej2basesdedatos.database.LawFirmDAO
import com.rdpp.ej2basesdedatos.databinding.ActivityMainBinding
import com.rdpp.ej2basesdedatos.dataclasses.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database : LawFirmDAO
    private lateinit var users : MutableList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.frameLogin.visibility = View.INVISIBLE

        LoadScreen().execute()

        binding.btnLogin.setOnClickListener {
            validateUser()
        }

        database = LawFirmDAO(this)
        users = getUsers()
    }

    private fun validateUser() {
        var login:String = binding.textUser.text.toString()
        var password:String  = binding.textPassword.text.toString()
        if (login.equals(users.get())){
            Snackbar.make(binding.root, "Empty fields", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun getUsers(): MutableList<User>{
        return database.getAllUsers()
    }

    private inner class LoadScreen() : AsyncTask<Void, Void, Void>() {
         override fun doInBackground(vararg p0: Void?): Void? {
             Glide.with(binding.root).load(getString(R.string.image_URL))
                 .centerCrop()
                 .diskCacheStrategy(DiskCacheStrategy.ALL)
                 .into(binding.imgLogo)
            Thread.sleep(3000)
             return null
         }

         override fun onPostExecute(result: Void?) {
             super.onPostExecute(result)
             binding.frameImage.visibility = View.INVISIBLE
             binding.frameLogin.visibility = View.VISIBLE
         }

     }


}