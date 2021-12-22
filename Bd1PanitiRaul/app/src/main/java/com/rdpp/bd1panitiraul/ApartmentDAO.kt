package com.rdpp.bd1panitiraul

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException

class ApartmentDAO(context: Context) {
    private val myDB: SQLiteDatabase

    companion object {
        val DATABASE_NAME = "apartmentsDB"
        val DATABASE_VERSION = 1
        val TABLE_NAME = "apartments"
    }

    init {
        val structure = ApartmentDB(context, DATABASE_NAME, null, DATABASE_VERSION)
        myDB = structure.writableDatabase
    }

    fun getAllApartments(): MutableList<Apartment> {
        val apartmentList: MutableList<Apartment> = ArrayList()
        val cursor: Cursor = myDB.query(TABLE_NAME, null, null, null,
            null, null, null)
        if (cursor.moveToFirst()) {
            do {
                apartmentList.add(Apartment(
                    cursor.getLong(0),
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("city")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("price")),
                    cursor.getString(cursor.getColumnIndexOrThrow("image")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("phone")),
                    cursor.getString(cursor.getColumnIndexOrThrow("email")),
                    cursor.getString(cursor.getColumnIndexOrThrow("details")),
                    cursor.getString(cursor.getColumnIndexOrThrow("activities")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("favorite"))))
            } while (cursor.moveToNext())
        }
        if (!cursor.isClosed) {
            cursor.close()
        }
        return apartmentList
    }
    fun getSearchedApartment(city: String): MutableList<Apartment>{
        val apartments: MutableList<Apartment> = ArrayList()
        val sql = "SELECT * from $TABLE_NAME where city= $city"
        val cursor: Cursor = myDB.rawQuery(sql, apartments)
    }
    fun insertFirst(){
        try {
            val values = ContentValues()
            val id = 1
            val price = 35
            val phone = 947202021
            values.put("id", 2)
            values.put("name", "Chalet")
            values.put("city", "Burgos")
            values.put("price", 50)
            values.put("image",
                "https://imgs.nestimg.com/vivienda_de_lujo_de_1075_m2_en_venta_burgos_castilla_y_leon_8130114617875993543.jpg")
            values.put("phone", 947202022)
            values.put("email", "vendo.micasa2@gmail.com")
            values.put("details",
                "Chalet en espacio abierto con varias habitaciones ideal para fiestas")
            values.put("activites", "Diversas actividades al aire libre")
            values.put("favorite", 0)
            val insertApartment =
                "INSERT INTO apartments (id, name, city, price, image, phone, email, details, activities, favorite)" +
                        " values ($id, 'Casa Rural', 'Burgos', $price, 'https://media.er2.co/es/burgos/0000000003755/635/0000000154659.jpg', $phone, 'vendo.micasa@gmail.com', 'Casa rural en Burgos', 'Escalada, Padel, Ciclismo', 0)"
            myDB.execSQL(insertApartment)
            myDB.insert("apartments", null, values)
        } catch (ex1: SQLiteException) {
            ex1.stackTrace
        }
    }
}