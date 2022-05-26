package com.rdpp.komorebi.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.SQLException

class KomorebiDB(
    context: Context, name: String, factory: SQLiteDatabase.CursorFactory?,
    version: Int,
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(database: SQLiteDatabase?) {
        try {
            val createUsersTable =
                "CREATE TABLE ${KomorebiDAO.TABLE_USERS} (user_Id TEXT PRIMARY KEY UNIQUE, " +
                        "user TEXT UNIQUE, password TEXT, profilePicture TEXT, userType INTEGER )"

            val createArticlesTable =
                "CREATE TABLE ${KomorebiDAO.TABLE_ARTICLES} (article_Id TEXT PRIMARY KEY UNIQUE, " +
                        "title TEXT UNIQUE, article TEXT, date TEXT, url TEXT, imgUrl TEXT)"

            val createWorkshopsTable =
                "CREATE TABLE ${KomorebiDAO.TABLE_WORKSHOPS} (list_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT UNIQUE)"

            val createListsProductsTable =
                "CREATE TABLE ${KomorebiDAO.TABLE_FORUM} (code INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "list_Id INTEGER, prod_Id INTEGER, quantity INTEGER)"


            database!!.execSQL(createUsersTable)
            database.execSQL(createArticlesTable)
            //database.execSQL(createWorkshopsTable)
            //database.execSQL(createListsProductsTable)


        } catch (e: SQLException) {
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}