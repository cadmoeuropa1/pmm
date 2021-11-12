package com.rdpp.dam2_raul_paniti_ejercicio1_bloque1

class User(login: String,  pass: String) {
    var log: String
    var password: String

    init {
        log = login
        password = pass
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (log != other.log) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = log.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }

}