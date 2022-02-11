package com.rdpp.fragmentPaniti.ui

import FragmentPaniti.R
import FragmentPaniti.databinding.ActivityMainBinding
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rdpp.fragmentPaniti.database.ProgrammersDAO
import com.rdpp.fragmentPaniti.dataclass.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: ProgrammersDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = ProgrammersDAO(this)
        binding.frameLogin.visibility = View.INVISIBLE
        binding.btnLogin.visibility = View.INVISIBLE

        Glide.with(binding.root)
            .load(getString(R.string.url_image))
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imgLogo)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                binding.frameLogin.visibility = View.VISIBLE
                binding.frameLogo.visibility = View.INVISIBLE
            }, 3000
        )
        loadUsers()

    }

    fun loadUsers() {
        val loginArray = resources.getStringArray(R.array.logins)
        val passArray = resources.getStringArray(R.array.passwords)
        val typesArray = resources.getStringArray(R.array.types)
        for (u in loginArray.indices) {
            val user = User(login = loginArray[u], pass = passArray[u], type = typesArray[u])
            database.addUser(user)
        }

        /*
        private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
            val formatter = SimpleDateFormat(format, locale)
            return formatter.format(this)
        }

        private fun getCurrentDateTime(): Date {
            return Calendar.getInstance().time
        }
        */
    }
}