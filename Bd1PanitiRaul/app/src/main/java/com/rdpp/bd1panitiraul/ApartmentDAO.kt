package com.rdpp.bd1panitiraul

import android.content.Context
import android.database.Cursor
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
        myDB = structure.writableDatabase
    }

    fun getAllApartments(): MutableList<Apartment> {
        val apartmentList: MutableList<Apartment> = ArrayList()
        val cursor: Cursor = myDB.query(TABLE_NAME, null, null, null,
            null, null, null)
        if (cursor.moveToFirst())
    }
}