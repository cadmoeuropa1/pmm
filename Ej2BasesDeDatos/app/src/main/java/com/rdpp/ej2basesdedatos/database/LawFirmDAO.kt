package com.rdpp.ej2basesdedatos.database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.rdpp.ej2basesdedatos.dataclasses.User

open class LawFirmDAO(context: Context) {
    private val mDB: SQLiteDatabase

    companion object {
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

    fun getAllUsers() : MutableList<User> {
        val list: MutableList<User> = ArrayList()
        val cursor: Cursor = mDB.query(TABLE_USERS, null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    User(
                        cursor.getString(cursor.getColumnIndexOrThrow("login")),
                        cursor.getString((cursor.getColumnIndexOrThrow("password")))
                    )
                )
            } while (cursor.moveToNext())
        }
        return list
    }
}