package com.rdpp.ej2basesdedatos.dataclasses

data class Case(
    val caseNum: Int,
    val name: String,
    val date: String,
    val details: String,
    val lawyer: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Case

        if (caseNum != other.caseNum) return false

        return true
    }

    override fun hashCode(): Int {
        return caseNum
    }
}