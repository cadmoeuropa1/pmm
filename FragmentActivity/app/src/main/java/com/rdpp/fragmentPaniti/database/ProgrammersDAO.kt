package com.rdpp.fragmentPaniti.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.rdpp.fragmentPaniti.dataclass.User

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


    fun addUser(user: User) {
        val values = ContentValues()
        values.put("login", user.login)
        values.put("pass", user.pass)
        values.put("type", user.type)
    }


}