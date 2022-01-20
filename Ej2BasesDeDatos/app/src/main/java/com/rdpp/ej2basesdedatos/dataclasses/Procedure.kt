package com.rdpp.ej2basesdedatos.dataclasses

data class Procedure(
    val procedureNum: Int,
    val caseNum: Int,
    val date: String,
    val details: String,
    val executed: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Procedure

        if (procedureNum != other.procedureNum) return false

        return true
    }

    override fun hashCode(): Int {
        return procedureNum
    }
}

