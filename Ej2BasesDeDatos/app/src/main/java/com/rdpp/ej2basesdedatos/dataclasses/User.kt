package com.rdpp.ej2basesdedatos.dataclasses

data class User(
    val reg_Num: String,
    val name: String,
    val login: String,
    val password: String,
    val type: String
) {
    constructor(login: String?, password: String?) : this("","","","","")


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (reg_Num != other.reg_Num) return false

        return true
    }

    override fun hashCode(): Int {
        return reg_Num.hashCode()
    }
}