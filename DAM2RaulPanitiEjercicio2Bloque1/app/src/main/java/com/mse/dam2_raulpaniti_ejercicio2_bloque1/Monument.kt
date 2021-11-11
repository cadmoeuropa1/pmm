package com.mse.dam2_raulpaniti_ejercicio2_bloque1

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Monument(
    var image: String, val name: String, val location: String, val mail: String,
    val phoneNumber: Uri, val description : String):Parcelable {

}
