package com.rdpp.ej2panitiraul

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Monument(
    var image: String, val name: String, val location: String, val mail: String,
    val phoneNumber: Uri, val description : String): Parcelable {

}
