package com.rdpp.bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class StoreBD(
    context: Context, name: String,
    factory: SQLiteDatabase.CursorFactory?, version: Int,
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(p0: SQLiteDatabase?) {
        try {
            val createStoreTable =
                "CREATE TABLE ${StoreDAO.TABLE_STORE} (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " name TEXT, favorite INTEGER)"
            p0!!.execSQL(createStoreTable)
        } catch (ex: SQLiteException) {
            ex.stackTrace
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}