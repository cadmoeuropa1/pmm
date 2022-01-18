package com.rdpp.ej2basesdedatos.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase

class LawFirmDAO(context: Context) {
    private val mDB: SQLiteDatabase
    companion object{
        val DATABASE_NAME = "law_firmDB"
        val DATABASE_VERSION = 1
        val TABLE_USERS = "users"
        val TABLE_CASES = "cases"
        val TABLE_PROCEDURES = "procedures"
    }

    init {
        val structure = LawFirmDB(context, DATABASE_NAME, null, DATABASE_VERSION)
        mDB = structure.writableDatabase
    }
}