package com.rdpp.bd2PanitiRaul.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.rdpp.bd2PanitiRaul.dataclasses.Case
import com.rdpp.bd2PanitiRaul.dataclasses.Procedure
import com.rdpp.bd2PanitiRaul.dataclasses.User

open class LawFirmDAO(context: Context) {
    private val mDB: SQLiteDatabase

    companion object {
        const val DATABASE_NAME = "law_firmDB"
        const val DATABASE_VERSION = 1
        const val TABLE_USERS = "users"
        const val TABLE_CASES = "cases"
        const val TABLE_PROCEDURES = "procedures"
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

    fun addCase(case: Case): Long {
        val result: Long
        val values = ContentValues()
        values.put("name", case.name)
        values.put("date", case.date)
        values.put("details", case.details)
        values.put("lawyer", case.lawyer)
        result = mDB.insert(TABLE_CASES, null, values)
        return result
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

    fun checkUser(regNum: String): Boolean {
        val sql = "SELECT * FROM $TABLE_USERS WHERE reg_Num = '$regNum'"
        val cursor: Cursor = mDB.rawQuery(sql, null)
        return cursor.moveToFirst()
    }

    fun getCaseProcedures(caseNum: Int?): MutableList<Procedure> {
        val list: MutableList<Procedure> = ArrayList()
        val sql = "select * from $TABLE_PROCEDURES where caseNum = '$caseNum'"
        val cursor: Cursor = mDB.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Procedure(
                        cursor.getInt(cursor.getColumnIndexOrThrow("procedureNum")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("caseNum")),
                        cursor.getString(cursor.getColumnIndexOrThrow("date")),
                        cursor.getString(cursor.getColumnIndexOrThrow("details")),
                        cursor.getString(cursor.getColumnIndexOrThrow("executed"))
                    )
                )
            } while (cursor.moveToNext())
            if (!cursor.isClosed)
                cursor.close()
        }
        return list
    }

    fun updateProcedure(procedure: Procedure) {
        val sql =
            "UPDATE $TABLE_PROCEDURES SET executed = 'YES' WHERE procedureNum = ${procedure.procedureNum}"
        mDB.execSQL(sql)
    }

    fun addProcedure(procedure: Procedure): Long {
        val result: Long
        val values = ContentValues()
        values.put("caseNum", procedure.caseNum)
        values.put("date", procedure.date)
        values.put("details", procedure.details)
        values.put("executed", procedure.executed)
        result = mDB.insert(TABLE_PROCEDURES, null, values)
        return result
    }
}
