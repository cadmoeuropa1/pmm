package com.rdpp.komorebi.model

data class Workshop(
    val id: String,
    val name: String,
    val info: String,
    val date: String = "",
    val url: String,
    val imgUrl: String
)
