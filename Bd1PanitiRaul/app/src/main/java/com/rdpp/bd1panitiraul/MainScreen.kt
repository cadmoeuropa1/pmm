package com.rdpp.bd1panitiraul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.rdpp.bd1panitiraul.databinding.ActivityMainScreenBinding

class MainScreen : AppCompatActivity(), EventsListener {
    lateinit var binding: ActivityMainScreenBinding
    private lateinit var adapterR: ApartmentAdapter
    private lateinit var gridLayout: GridLayoutManager
    private lateinit var db: ApartmentDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = ApartmentDAO(this)
        db.insertFirst()

    }

    private fun startRecyclerView() {
        adapterR = ApartmentAdapter(mutableListOf(), this)
        gridLayout = GridLayoutManager(this, 2)
        getAllApartments()

        binding.recyclerview.apply {
            setHasFixedSize(true)
            adapter = adapterR
            layoutManager = gridLayout
        }
    }

    private fun getAllApartments() {
        val apartment = db.getAllApartments()
        adapterR.setApartments(apartment)
    }

    override fun onFavorite(apartment: Apartment) {
        TODO("Not yet implemented")
    }
}