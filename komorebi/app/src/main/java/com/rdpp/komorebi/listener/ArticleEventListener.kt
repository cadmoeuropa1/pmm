package com.rdpp.komorebi.listener

import com.rdpp.komorebi.model.Article

interface ArticleEventListener {
    fun shareArticle(article: Article) {}

    fun viewArticle(article: Article) {}
}