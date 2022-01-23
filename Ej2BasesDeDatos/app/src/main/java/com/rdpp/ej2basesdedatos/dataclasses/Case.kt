package com.rdpp.ej2basesdedatos.dataclasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Case(
    val caseNum: Int? = 0,
    val name: String,
    val date: String,
    val details: String,
    val lawyer: String
) : Parcelable {

    constructor(name: String, date: String, details: String, lawyer: String) : this(
        0,
        name,
        date,
        details,
        lawyer
    )
}