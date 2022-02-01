package com.rdpp.bd3panitiraul.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.rdpp.bd3panitiraul.dataclass.Category
import com.rdpp.bd3panitiraul.dataclass.Product
import com.rdpp.bd3panitiraul.dataclass.ShoppingList

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

    fun categoriesData(): Boolean {
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
        return mDB.delete(TABLE_CATEGORIES, "name='$cat'", null)
    }

    fun getProductsByCategories(): MutableList<Product> {
        val list = ArrayList<Product>()
        val sql = "SELECT * FROM $TABLE_PRODUCTS ORDER BY cat_Id"
        val cursor = mDB.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
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
        return list
    }

    fun getProductsAlphabetically(): MutableList<Product> {
        val list = ArrayList<Product>()
        val sql = "SELECT * FROM $TABLE_PRODUCTS ORDER BY name ASC"
        val cursor = mDB.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
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
        return list
    }

    fun addProduct(prodName: String, cat: Category, prodImage: String): Long {
        val values = ContentValues()
        values.put("name", prodName)
        values.put("cat_Id", cat.cat_Id)
        values.put("image", prodImage)

        return mDB.insert(TABLE_PRODUCTS, null, values)
    }

    fun deleteProduct(product: String): Int {
        return mDB.delete(TABLE_PRODUCTS, "name='$product'", null)
    }

    fun getProductCategory(catId: Int): String {
        var result = ""
        val cursor = mDB.rawQuery("SELECT name FROM $TABLE_CATEGORIES WHERE cat_Id='$catId'", null)
        if (cursor.moveToFirst()) {
            result = cursor.getString(cursor.getColumnIndexOrThrow("name"))
        }
        return result
    }

    fun addProductToList(product: Product, shoppingList: ShoppingList) {
        TODO("Not yet implemented")
    }

    fun getAllLists(): MutableList<ShoppingList> {
        val shoppingLists = ArrayList<ShoppingList>()
        val sql = "SELECT * FROM $TABLE_LISTS"
        val cursor: Cursor = mDB.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                shoppingLists.add(
                    ShoppingList(
                        cursor.getInt(cursor.getColumnIndexOrThrow("list_Id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name"))
                    )
                )
            } while (cursor.moveToNext())
        }
        if (cursor.isClosed) {
            cursor.close()
        }
        return shoppingLists
    }

    fun addList(listName: String): Long {
        val contentValues = ContentValues()
        contentValues.put("name", listName)
        return mDB.insert(TABLE_LISTS, null, contentValues)
    }

    fun shoppingListsData(): Boolean {
        var semaforo = false
        val cursor: Cursor = mDB.rawQuery("SELECT * FROM $TABLE_LISTS", null)
        if (cursor.moveToNext()) {
            semaforo = true
        }
        cursor.close()
        return semaforo
    }
}
