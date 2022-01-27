package com.rdpp.bd3panitiraul.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.SQLException

class ShoppingListDB(
    context: Context, name: String, factory: SQLiteDatabase.CursorFactory?,
    version: Int,
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(database: SQLiteDatabase?) {
        try {
            val createProductsTable =
                "CREATE TABLE ${ShoppingListDAO.TABLE_PRODUCTS} (prod_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT UNIQUE, cat_Id INTEGER, image TEXT)"

            val createCategoriesTable =
                "CREATE TABLE ${ShoppingListDAO.TABLE_CATEGORIES} (cat_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT UNIQUE)"

            val createListsTable =
                "CREATE TABLE ${ShoppingListDAO.TABLE_LISTS} (list_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT UNIQUE)"

            val createListsProductsTable =
                "CREATE TABLE ${ShoppingListDAO.TABLE_LISTS_PRODUCTS} (code INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "list_Id INTEGER, prod_Id INTEGER, quantity INTEGER)"


            database!!.execSQL(createProductsTable)
            database.execSQL(createCategoriesTable)
            database.execSQL(createListsTable)
            database.execSQL(createListsProductsTable)


        } catch (e: SQLException) {
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}