package com.rdpp.proyecto

data class Producto(val nombre:String, val precio:Float){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Producto

        if (nombre != other.nombre) return false
        if (precio != other.precio) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nombre.hashCode()
        result = 31 * result + precio.hashCode()
        return result
    }
}

