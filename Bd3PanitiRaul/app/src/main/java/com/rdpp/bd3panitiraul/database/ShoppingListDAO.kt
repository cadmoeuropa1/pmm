package com.rdpp.bd3panitiraul.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.rdpp.bd3panitiraul.dataclass.Category
import com.rdpp.bd3panitiraul.dataclass.Product

open class ShoppingListDAO(context: Context) {


    private val mDB: SQLiteDatabase

    companion object {
        const val DATABASE_NAME = "ShoppingList"
        const val DATABASE_VERSION = 1
        const val TABLE_PRODUCTS = "products"
        const val TABLE_CATEGORIES = "categories"
        const val TABLE_LISTS = "lists"
        const val TABLE_LISTS_PRODUCTS = "lists_products"
    }

    init {
        val structure = ShoppingListDB(context, DATABASE_NAME, null, DATABASE_VERSION)
        mDB = structure.writableDatabase

    }

    fun getAllCategories(): MutableList<Category> {
        val categories: MutableList<Category> = ArrayList()
        val sql = "SELECT * FROM $TABLE_CATEGORIES"
        val cursor: Cursor = mDB.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                categories.add(
                    Category(
                        cursor.getInt(cursor.getColumnIndexOrThrow("cat_Id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name"))
                    )
                )
            } while (cursor.moveToNext())

        }
        if (cursor.isClosed) {
            cursor.close()
        }
        return categories
    }

    fun getAllProducts(): MutableList<Product> {
        val productList = ArrayList<Product>()
        val sql = "SELECT * FROM $TABLE_PRODUCTS"
        val cursor: Cursor = mDB.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                productList.add(
                    Product
                        (
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("cat_Id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("image"))
                    )
                )
            } while (cursor.moveToNext())
        }
        if (cursor.isClosed) {
            cursor.close()
        }
        return productList
    }

    fun CategoriesData(): Boolean {

        var semaforo = false

        val cursor: Cursor = mDB.rawQuery("SELECT * FROM $TABLE_CATEGORIES", null)

        if (cursor.moveToNext()) {

            semaforo = true

        }
        cursor.close()
        return semaforo
    }

    fun addCategory(cat: String): Long {
        val result: Long
        val values = ContentValues()
        values.put("name", cat)
        result = mDB.insert(TABLE_CATEGORIES, null, values)

        return result
    }

    fun deleteCategory(cat: String): Int {
        val values = ContentValues()
        values.put("name", cat)

        return mDB.delete(TABLE_CATEGORIES, "name='$cat'", null)
    }


}