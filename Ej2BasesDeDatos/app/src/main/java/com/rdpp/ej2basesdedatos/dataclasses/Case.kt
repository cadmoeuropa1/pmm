package com.rdpp.ej2basesdedatos.dataclasses

data class Case(
    val caseNum: Int,
    val name: String,
    val date: String,
    val details: String,
    val lawyer: String
) {
    constructor(name: String, date: String, details: String, lawyer: String) : this(
        0,
        name,
        date,
        details,
        lawyer
    )

}