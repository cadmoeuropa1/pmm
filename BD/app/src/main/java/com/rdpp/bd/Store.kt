package com.rdpp.bd

data class Store(var id: Long = 0, var name: String,var phone: String, var webSite:String="",
                 var photoUrl: String, var isFavorite: Int=0){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Store

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
