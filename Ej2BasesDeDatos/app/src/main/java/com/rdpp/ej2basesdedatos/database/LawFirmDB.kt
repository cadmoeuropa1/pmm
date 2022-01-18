package com.rdpp.ej2basesdedatos.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class LawFirmDB (context: Context, name: String, factory: SQLiteDatabase.CursorFactory?,
                 version: Int,
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createUserTable = "CREATE TABLE ${LawFirmDAO.TABLE_USERS} (reg_Num TEXT PRIMARY KEY, " +
                "name TEXT, login TEXT UNIQUE, password TEXT, type TEXT)"

        val createCasesTable = "CREATE TABLE ${LawFirmDAO.TABLE_CASES} (caseNum AUTOINCREMENT PRIMARY KEY, " +
                "name TEXT, date TEXT, details TEXT, lawyer TEXT)"

        val createProceduresTable = "CREATE TABLE ${LawFirmDAO.TABLE_PROCEDURES} (caseNum AUTOINCREMENT PRIMARY KEY, " +
                "name TEXT, date TEXT, details TEXT, lawyer TEXT)"

        db!!.execSQL(createUserTable)
        db.execSQL(createCasesTable)
        db.execSQL(createProceduresTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}