package com.rdpp.bd1panitiraul

import android.content.Context
import android.database.sqlite.SQLiteDatabase

class ApartmentDAO(context: Context) {
    private val myDB: SQLiteDatabase

    companion object {
        val DATABASE_NAME = "apartmentsDB"
        val DATABASE_VERSION = 1
        val TABLE_NAME = "apartments"
    }
    init {
        val structure = ApartmentDB(context, DATABASE_NAME, null, DATABASE_VERSION)
        myDB =structure.writableDatabase
    }
}