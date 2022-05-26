package com.rdpp.komorebi.model

data class Article(
    val id: String,
    val name: String,
    val article: String,
    val date: String = "",
    val url: String,
    val imgUrl: String
) {

}
