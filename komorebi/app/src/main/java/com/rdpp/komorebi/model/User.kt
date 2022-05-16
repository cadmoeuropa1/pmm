package com.rdpp.komorebi.model

data class User(
    val userId: String,
    val user: String,
    val password: String,
    val uType: Int,
    val profilePicture: String
)
