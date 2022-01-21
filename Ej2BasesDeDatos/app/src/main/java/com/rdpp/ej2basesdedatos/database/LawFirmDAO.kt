package com.rdpp.ej2basesdedatos.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.rdpp.ej2basesdedatos.dataclasses.Case
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

    fun addUser(user: User) {
        val values = ContentValues()
        values.put("reg_Num", user.reg_Num)
        values.put("name", user.name)
        values.put("login", user.login)
        values.put("password", user.password)
        values.put("type", user.type)
        mDB.insert(TABLE_USERS, null, values)

    }

    fun addCase(case: Case) {
        val values = ContentValues()
        values.put("name", case.name)
        values.put("date", case.date)
        values.put("details", case.details)
        values.put("lawyer", case.lawyer)
        mDB.insert(TABLE_CASES, null, values)
    }


    fun getUser(login: String, password: String): User? {
        var user: User? = null
        val sql = "SELECT * from $TABLE_USERS WHERE login='$login' AND password='$password'"
        val cursor: Cursor = mDB.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            user = User(
                cursor.getString(cursor.getColumnIndexOrThrow("reg_Num")),
                cursor.getString(cursor.getColumnIndexOrThrow("name")),
                cursor.getString(cursor.getColumnIndexOrThrow("login")),
                cursor.getString(cursor.getColumnIndexOrThrow("password")),
                cursor.getString(cursor.getColumnIndexOrThrow("type"))
            )
        }
        return user
    }

    fun getTables(): Boolean {
        var sem = false
        val cursor: Cursor =
            mDB.rawQuery("SELECT * FROM $TABLE_USERS, $TABLE_USERS, $TABLE_USERS", null)
        if (cursor.moveToNext()) {
            sem = true
        }
        return sem
    }

    fun getAllCases(): MutableList<Case> {
        val list: MutableList<Case> = ArrayList()
        val cursor: Cursor = mDB.query(TABLE_CASES, null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Case(
                        cursor.getInt(cursor.getColumnIndexOrThrow("caseNum")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("date")),
                        cursor.getString(cursor.getColumnIndexOrThrow("details")),
                        cursor.getString(cursor.getColumnIndexOrThrow("lawyer"))
                    )
                )
            } while (cursor.moveToNext())
            if (!cursor.isClosed)
                cursor.close()
        }
        return list
    }

    fun getLawyerCases(regNum: String): MutableList<Case> {
        val list: MutableList<Case> = ArrayList()
        val sql = "SELECT * FROM cases WHERE lawyer ='$regNum'"
        val cursor: Cursor = mDB.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Case(
                        cursor.getInt(cursor.getColumnIndexOrThrow("caseNum")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("date")),
                        cursor.getString(cursor.getColumnIndexOrThrow("details")),
                        cursor.getString(cursor.getColumnIndexOrThrow("lawyer"))
                    )
                )
            } while (cursor.moveToNext())
            if (!cursor.isClosed)
                cursor.close()
        }
        return list
    }

}