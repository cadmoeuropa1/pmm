package com.rdpp.fragmentPaniti.ui

import FragmentPaniti.R
import FragmentPaniti.databinding.ActivityMainScreenBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.rdpp.fragmentPaniti.database.ProgrammersDAO
import com.rdpp.fragmentPaniti.dataclass.User

class MainScreen : AppCompatActivity() {
    private lateinit var binding: ActivityMainScreenBinding
    private lateinit var database: ProgrammersDAO
    private lateinit var fragmentTransaction: FragmentTransaction
    private var user: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        database = ProgrammersDAO(this)
        user = intent.getParcelableExtra<User>("user")
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        if (binding.fragContainer == null) {
            startOnTablet(savedInstanceState, user!!)
        } else {
            startOnSmartphone(savedInstanceState, user!!)
        }

        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun addEvent() {
        val fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = AddEventFragment()
        if (binding.fragContainer != null) {
            fragmentTransaction.replace(R.id.fragContainer, fragment)
        } else {
            fragmentTransaction.add(R.id.fragmentContainer, fragment)
        }
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun startOnSmartphone(savedInstanceState: Bundle?, user: User) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = ConsultEventFragment()
        val args = Bundle()
        args.putInt("user_Id", user.idUser)
        fragment.arguments = args
        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.fragContainer, fragment)
        } else {
            fragmentTransaction.replace(R.id.fragContainer, fragment)
        }
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun startOnTablet(savedInstanceState: Bundle?, user: User) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = ConsultEventFragment()
        val fragment2 = AddEventFragment()
        val args = Bundle()
        args.putInt("user_Id", user.idUser)
        fragment.arguments = args
        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.mainContainer, fragment)
            fragmentTransaction.add(R.id.fragmentContainer, fragment)
        } else {
            fragmentTransaction.replace(R.id.mainContainer, fragment)
            fragmentTransaction.replace(R.id.fragmentContainer, fragment2)

        }
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun editEvent(idEvent: Int) {
        val fragment = EditEventFragment()
        if (binding.fragmentContainer == null) {
            fragmentTransaction.replace(R.id.fragContainer, fragment)
        } else {
            fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        }

    }
}