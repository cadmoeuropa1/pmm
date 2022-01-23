package com.rdpp.ej2basesdedatos.dataclasses

data class Procedure(
    val procedureNum: Int? = 0,
    val caseNum: Int,
    val date: String,
    val details: String,
    val executed: String
) {
    constructor(caseNum: Int, date: String, details: String, executed: String) : this(
        0,
        caseNum,
        date,
        details,
        executed
    )
}


