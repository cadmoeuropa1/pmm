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

            val insertUser1 =
                "INSERT INTO ${LawFirmDAO.TABLE_USERS} VALUES('0', 'PedroPa', 'pedro', '123', 'L')"
            val insertUser2 =
                "INSERT INTO ${LawFirmDAO.TABLE_USERS} VALUES('1', 'Adrian', 'adri', '123', 'S')"

            val insertCase1 =
                "INSERT INTO ${LawFirmDAO.TABLE_CASES} ('name','date','details','lawyer') VALUES('Pokemon', '09/2012', 'Corruption case in Galicia', '0')"
            val insertCase2 =
                "INSERT INTO ${LawFirmDAO.TABLE_CASES} ('name','date','details','lawyer') VALUES('Construction Case', '02/1994', 'Corruption construction case in Burgos', '0')"

            val insertProcedure1 =
                "INSERT INTO ${LawFirmDAO.TABLE_PROCEDURES} ('caseNum','date','details','executed') VALUES(1, '10/2012', 'Interrogation with suspects', 'YES')"
            val insertProcedure2 =
                "INSERT INTO ${LawFirmDAO.TABLE_PROCEDURES} ('caseNum','date','details','executed')VALUES(0, '03/1994', 'Information gathering', 'NO')"

            db!!.execSQL(createUserTable)
            db.execSQL(createCasesTable)
            db.execSQL(createProceduresTable)

            db.execSQL(insertUser1)
            db.execSQL(insertUser2)
            db.execSQL(insertCase1)
            db.execSQL(insertCase2)
            db.execSQL(insertProcedure1)
            db.execSQL(insertProcedure2)

        } catch (e: SQLException) {
        }

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}