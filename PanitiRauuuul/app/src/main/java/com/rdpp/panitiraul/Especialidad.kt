package com.rdpp.panitiraul

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Especialidad(var codigoEspecialidad: Int, var nombre: String, var annos: Int) :
    Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Especialidad

        if (codigoEspecialidad != other.codigoEspecialidad) return false

        return true
    }

    override fun hashCode(): Int {
        return codigoEspecialidad
    }

}
