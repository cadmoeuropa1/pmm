package com.rdpp.komorebi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rdpp.komorebi.R
import com.rdpp.komorebi.database.KomorebiDAO
import com.rdpp.komorebi.databinding.NewsCardLayoutBinding
import com.rdpp.komorebi.listener.ArticleEventListener
import com.rdpp.komorebi.model.Article

class ArticleAdapter(
    private var articles: MutableList<Article>,
    private var listener: ArticleEventListener
) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private lateinit var context: Context


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = NewsCardLayoutBinding.bind(view)
        fun setListener(position: Int, article: Article) {
            binding.btnShare.setOnClickListener {
                TODO("COMPARTIR NOTICIA (BUSCAR EN GOOGLE, MAX PRIORIDAD)")
            }
            binding.btnView.setOnClickListener {
                TODO("VISUALIZAR LA NOTICIA COMPLETA (FRAGMENT)")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.news_card_layout, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = this.articles[position]
        with(holder) {
            setListener(position, article)
            binding.txtTitle.text = article.name
            binding.txtDate.text = article.date
        }
    }

    override fun getItemCount() = articles.size

    fun setArticles(articlesR: MutableList<Article>?) {
        if (articlesR != null) {
            articles = articlesR
        }
    }
}