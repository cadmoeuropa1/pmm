package com.rdpp.komorebi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.rdpp.komorebi.R
import com.rdpp.komorebi.adapter.ArticleAdapter
import com.rdpp.komorebi.database.KomorebiDAO
import com.rdpp.komorebi.databinding.ActivityNewsBinding
import com.rdpp.komorebi.listener.ArticleEventListener

class NewsActivity : AppCompatActivity(), ArticleEventListener {
    private lateinit var binding: ActivityNewsBinding
    private lateinit var database: KomorebiDAO
    private lateinit var adapterR: ArticleAdapter
    private lateinit var layManager: GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNewsBinding.inflate(layoutInflater)
        database = KomorebiDAO(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        adapterR = ArticleAdapter(mutableListOf(), this)
        val articles = database.getAllArticles()
        layManager = GridLayoutManager(this, 1)
        adapterR.setArticles(articles)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = adapterR
            layoutManager = layManager
        }


    }
}