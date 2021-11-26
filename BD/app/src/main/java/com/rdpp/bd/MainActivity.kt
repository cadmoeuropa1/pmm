package com.rdpp.bd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rdpp.bd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), EventListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: StoreAdapter
    private lateinit var gridLayout: GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startRecyclerView()
    }

    private fun startRecyclerView() {
        adapter = StoreAdapter(mutableListOf(), this)
        gridLayout = GridLayoutManager(this, 2)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = adapter
            layoutManager = gridLayout
        }
    }

    override fun edit(id: Long) {
        TODO("Not yet implemented")
    }
}