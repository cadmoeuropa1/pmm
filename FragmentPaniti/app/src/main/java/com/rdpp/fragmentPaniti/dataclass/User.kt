package com.rdpp.fragmentPaniti.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val idUser: Int = 0, val login: String, val pass: String, val type: String) :
    Parcelable {

}
