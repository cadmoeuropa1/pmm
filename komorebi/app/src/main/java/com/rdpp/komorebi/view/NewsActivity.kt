package com.rdpp.komorebi.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.rdpp.komorebi.R
import com.rdpp.komorebi.adapter.ArticleAdapter
import com.rdpp.komorebi.database.KomorebiDAO
import com.rdpp.komorebi.databinding.ActivityNewsBinding
import com.rdpp.komorebi.listener.ArticleEventListener
import com.rdpp.komorebi.model.Article

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

    override fun shareArticle(article: Article) {

        val shareIntent = Intent()
        with(shareIntent) {
            action = Intent.ACTION_SEND
            setType("text/plain")
            putExtra(Intent.EXTRA_TEXT, article.url)
        }
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to_intent)))
    }

    override fun viewArticle(article: Article) {
        val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
        startActivity(viewIntent)
    }
}