package com.rdpp.panitiraul

data class Producto(
    val prod_Id: Int = 0,
    val cat_Id: Int,
    val nombre: String,
    val pvp: Int,
    val imagen: String,
    val color: String,
    val talla: String
)
