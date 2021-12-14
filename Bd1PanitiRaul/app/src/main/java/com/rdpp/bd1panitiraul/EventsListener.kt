package com.rdpp.bd1panitiraul

interface EventsListener {
    fun edit(id: Long)
    fun onFavorite(apartment: Apartment)
    fun deleteStore(id: Long)
}