package com.rdpp.fragmentPaniti.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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

    fun getUser(login: String, password: String): User? {
        var user: User? = null
        val sql = "SELECT * from $TABLE_USER WHERE login='$login' AND pass='$password'"
        val cursor: Cursor = database.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            user = User(
                cursor.getInt(cursor.getColumnIndexOrThrow("user_Id")),
                cursor.getString(cursor.getColumnIndexOrThrow("login")),
                cursor.getString(cursor.getColumnIndexOrThrow("pass")),
                cursor.getString(cursor.getColumnIndexOrThrow("type"))
            )
        }
        return user
    }


}