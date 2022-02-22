package com.rdpp.fragmentPaniti.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class ProgrammersDB(
    context: Context, name: String, factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val createEventsTable =
                "CREATE TABLE ${ProgrammersDAO.TABLE_EVENTS} (event_Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " date DATE, title TEXT, description TEXT)"

            val createUserEventTable =
                "CREATE TABLE ${ProgrammersDAO.TABLE_USER_EVENT} (userEvent_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "user_Id INTEGER, event_Id INTEGER)"

            val createUserTable =
                "CREATE TABLE ${ProgrammersDAO.TABLE_USER} (user_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "login TEXT, pass TEXT, type TEXT(1))"

            db!!.execSQL(createEventsTable)
            db.execSQL(createUserEventTable)
            db.execSQL(createUserTable)
        } catch (ex: SQLiteException) {

        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}