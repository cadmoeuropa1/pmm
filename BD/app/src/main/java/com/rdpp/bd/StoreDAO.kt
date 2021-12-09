package com.rdpp.bd

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class StoreDAO(context: Context) {
    private val mBD: SQLiteDatabase

    companion object {
        val DATABASE_NAME = "storesbd"
        val DATABASE_VERSION = 1
        val TABLE_STORE = "stores"
    }

    init {
        val structure = StoreBD(context, DATABASE_NAME, null, DATABASE_VERSION)
        mBD = structure.writableDatabase
    }

    fun addStore(store: Store) {
        val values = ContentValues()
        values.put("name", store.name)
        values.put("favorite", store.isFavorite)
        mBD.insert(TABLE_STORE, null, values)
    }

    fun getAllStores(): MutableList<Store> {
        val storeList: MutableList<Store> = ArrayList()
        val cursor: Cursor = mBD.query(TABLE_STORE, null, null, null,
            null, null, null)
        if (cursor.moveToFirst()) {
            do {
                storeList.add(Store(cursor.getLong(0),
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("favorite"))))
            } while (cursor.moveToNext())
        }
        if (!cursor.isClosed) {
            cursor.close()
        }
        return storeList
    }

    fun updateStore(store: Store) {
        val values = ContentValues()
        values.put("name", store.name)
        values.put("favorite", store.isFavorite)
        var args = arrayOf(store.id.toString())
        mBD.update(TABLE_STORE, values, "id=?", args)
    }
    fun deleteStore(id: Long){
        val args = arrayOf(id.toString())
        mBD.delete(TABLE_STORE, "id=?", args)
    }
}