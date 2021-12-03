package com.rdpp.bd

interface EventListener {
    fun edit(id: Long)
    fun onFavorite(store: Store)
    fun deleteStore(id: Long)
}