package com.rdpp.bd3panitiraul.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase

open class ShoppingListDAO (context : Context){
    private val mDB: SQLiteDatabase

    companion object {
        const val DATABASE_NAME = "ShoppingList"
        const val DATABASE_VERSION = 1
        const val TABLE_PRODUCTS = "products"
        const val TABLE_CATEGORIES = "categories"
        const val TABLE_LISTS = "lists"
        const val TABLE_LISTS_PRODUCTS = "lists_products"
    }
    init {
        val structure = ShoppingListDB(context, DATABASE_NAME, null, DATABASE_VERSION)
        mDB = structure.writableDatabase

    }

}