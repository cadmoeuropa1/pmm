package com.rdpp.bd1panitiraul

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class ApartmentDB(context:Context, name:String, factory:SQLiteDatabase.CursorFactory?,
version:Int): SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(p0: SQLiteDatabase?) {
        try{
            val createApartmentTable = "CREATE TABLE apartments (id INTEGER PRIMARY KEY, " +
                    "name TEXT, city TEXT, price INTEGER, image TEXT, phone INTEGER, mail TEXT, " +
                    "details TEXT, activities TEXT, favorite INTEGER)"
            p0!!.execSQL(createApartmentTable)
        }catch (ex: SQLiteException){
            ex.stackTrace
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}