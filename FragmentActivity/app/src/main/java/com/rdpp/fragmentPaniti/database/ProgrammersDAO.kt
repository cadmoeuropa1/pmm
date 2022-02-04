package com.rdpp.fragmentPaniti.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase

class ProgrammersDAO(context: Context) {
    private val database: SQLiteDatabase

    companion object {
        const val DATABASE_NAME = "Programmers"
        const val DATABASE_VERSION = 1
        const val TABLE_EVENTS = "Events"
        const val TABLE_USER_EVENT = "User-Events"
        const val TABLE_USER = "User"
    }

    init {
        val structure = ProgrammersDB(context, DATABASE_NAME, null, DATABASE_VERSION)
        database = structure.writableDatabase
    }

}