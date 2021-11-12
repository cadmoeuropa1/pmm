package com.rdpp.intents

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class User(var name: String?, var surname:String?): Parcelable {

}
