package com.rdpp.komorebi.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.rdpp.komorebi.R
import com.rdpp.komorebi.adapter.WorkshopAdapter
import com.rdpp.komorebi.database.KomorebiDAO
import com.rdpp.komorebi.databinding.ActivityWorkshopBinding
import com.rdpp.komorebi.listener.WorkshopEventListener
import com.rdpp.komorebi.model.Workshop

class WorkshopActivity : AppCompatActivity(), WorkshopEventListener {

    private lateinit var binding: ActivityWorkshopBinding
    private lateinit var database: KomorebiDAO
    private lateinit var adapterR: WorkshopAdapter
    private lateinit var layManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWorkshopBinding.inflate(layoutInflater)
        database = KomorebiDAO(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapterR = WorkshopAdapter(mutableListOf(), this)
        val workshops = database.getAllWorkshops()
        layManager = GridLayoutManager(this, 1)
        adapterR.setWorkshops(workshops)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = adapterR
            layoutManager = layManager
        }
    }

    override fun shareWorkshop(workshop: Workshop) {
        val shareIntent = Intent()
        with(shareIntent) {
            action = Intent.ACTION_SEND
            putExtra("url", workshop.url)
        }
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to_intent)))
    }

    override fun viewWorkshop(workshop: Workshop) {
        super.viewWorkshop(workshop)
    }


}