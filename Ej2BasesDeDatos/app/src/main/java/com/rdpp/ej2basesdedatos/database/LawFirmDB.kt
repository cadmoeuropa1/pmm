package com.rdpp.ej2basesdedatos.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.SQLException

class LawFirmDB(
    context: Context, name: String, factory: SQLiteDatabase.CursorFactory?,
    version: Int,
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val createUserTable =
                "CREATE TABLE ${LawFirmDAO.TABLE_USERS} (reg_Num TEXT PRIMARY KEY, " +
                        "name TEXT, login TEXT UNIQUE, password TEXT, type TEXT)"

            val createCasesTable =
                "CREATE TABLE ${LawFirmDAO.TABLE_CASES} (caseNum INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT, date TEXT, details TEXT, lawyer TEXT)"

            val createProceduresTable =
                "CREATE TABLE ${LawFirmDAO.TABLE_PROCEDURES} (procedureNum INTEGER PRIMARY KEY AUTOINCREMENT , caseNum INTEGER, " +
                        "date TEXT, details TEXT, executed TEXT)"


            db!!.execSQL(createUserTable)
            db.execSQL(createCasesTable)
            db.execSQL(createProceduresTable)


        } catch (e: SQLException) {
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


}