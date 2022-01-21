package com.rdpp.ej2basesdedatos.dataclasses

data class Case(
    val caseNum: Int = 0,
    val name: String,
    val date: String,
    val details: String,
    val lawyer: String
) {


}