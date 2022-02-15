package com.rdpp.panitiraul

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class EmpresaDB(
    context: Context, name: String, factory: SQLiteDatabase.CursorFactory?,
    version: Int,
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(database: SQLiteDatabase?) {
        try {
            val createTablaCategorias =
                "CREATE TABLE ${EmpresaDAO.TABLA_CATEGORIAS} (cat_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombre TEXT)"
            val createTablaProductos =
                "CREATE TABLE ${EmpresaDAO.TABLA_PRODUCTOS} (prod_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cat_Id INTEGER, nombre TEXT UNIQUE, pvp INTEGER, imagen TEXT, color TEXT, talla INTEGER)"
            val createTablaUsuarios =
                "CREATE TABLE ${EmpresaDAO.TABLA_USUARIOS} (usu_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "login TEXT UNIQUE, contra TEXT, nombre TEXT)"
            val createTablaListaDeDeseos =
                "CREATE TABLE ${EmpresaDAO.TABLA_LISTA_DE_DESEOS} (lista_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "usu_Id INTEGER, prod_Id INTEGER UNIQUE)"

            database!!.execSQL(createTablaCategorias)
            database.execSQL(createTablaProductos)
            database.execSQL(createTablaUsuarios)
            database.execSQL(createTablaListaDeDeseos)
        } catch (ex: SQLiteException) {
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}