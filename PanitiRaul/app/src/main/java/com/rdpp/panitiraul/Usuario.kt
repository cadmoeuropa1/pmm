package com.rdpp.panitiraul

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario(val usu_Id: Int = 0, val login: String, val contra: String, val nombre: String) :
    Parcelable
