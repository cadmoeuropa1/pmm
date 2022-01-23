package com.rdpp.bd2PanitiRaul.dataclasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val reg_Num: String,
    val name: String,
    val login: String,
    val password: String,
    val type: String,
) : Parcelable {
    constructor(login: String?, password: String?) : this("", "", "", "", "")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (login != other.login) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = login.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }

}