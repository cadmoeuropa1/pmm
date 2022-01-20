package com.rdpp.ej2basesdedatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.rdpp.ej2basesdedatos.database.LawFirmDAO
import com.rdpp.ej2basesdedatos.databinding.ActivityMainScreenLawyerBinding
import com.rdpp.ej2basesdedatos.dataclasses.Case
import com.rdpp.ej2basesdedatos.interfaces.EventListener

class MainScreen_Lawyer : AppCompatActivity(), EventListener {
    lateinit var binding: ActivityMainScreenLawyerBinding
    lateinit var db : LawFirmDAO
    lateinit var adapter: CaseAdapter
    lateinit var gridLayout : GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenLawyerBinding.inflate(layoutInflater)
        db = LawFirmDAO(this)
        adapter = CaseAdapter(mutableListOf(), this)
        setContentView(binding.root)
        //startRecycler()
    }

    private fun startRecycler() {
        adapter = CaseAdapter(mutableListOf(), this)
        gridLayout = GridLayoutManager(this, 1)

    }

    override fun showDetails(case: Case) {
        TODO("Not yet implemented")
    }
}