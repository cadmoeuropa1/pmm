package com.rdpp.panitiraul

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class EmpresaDAO(context: Context) {

    private val mDB: SQLiteDatabase

    companion object {
        const val DATABASE_NAME = "Empresa"
        const val DATABASE_VERSION = 1
        const val TABLA_CATEGORIAS = "categorias"
        const val TABLA_PRODUCTOS = "productos"
        const val TABLA_USUARIOS = "usuarios"
        const val TABLA_LISTA_DE_DESEOS = "lista_de_deseos"
    }

    init {
        val structure = EmpresaDB(context, DATABASE_NAME, null, DATABASE_VERSION)
        mDB = structure.writableDatabase

    }

    fun annadirCategorias(categoriasR: ArrayList<Categoria>) {
        for (c in categoriasR) {
            val values = ContentValues()
            values.put("nombre", c.nombre)
            mDB.insert(TABLA_CATEGORIAS, null, values)
        }
    }

    fun annadirProductos(productosR: java.util.ArrayList<Producto>) {
        for (p in productosR) {
            val values = ContentValues()
            values.put("cat_Id", p.cat_Id)
            values.put("nombre", p.nombre)
            values.put("pvp", p.pvp)
            values.put("imagen", p.imagen)
            values.put("color", p.color)
            values.put("talla", p.talla)
            mDB.insert(TABLA_PRODUCTOS, null, values)
        }
    }

    fun annadirUsuarios(usuariosR: ArrayList<Usuario>) {
        for (u in usuariosR) {
            val values = ContentValues()
            values.put("login", u.login)
            values.put("contra", u.contra)
            values.put("nombre", u.nombre)
            mDB.insert(TABLA_USUARIOS, null, values)
        }
    }

    private fun getAllUsuarios(): ArrayList<Usuario> {
        val usuarios = ArrayList<Usuario>()
        val sql = "SELECT * FROM $TABLA_USUARIOS"
        val cursor = mDB.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                usuarios.add(
                    Usuario(
                        login = cursor.getString(cursor.getColumnIndexOrThrow("login")),
                        contra = cursor.getString(cursor.getColumnIndexOrThrow("contra")),
                        nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                    )
                )
            } while (cursor.moveToNext())
        }
        return usuarios
    }

    fun getUsuario(login: String, contra: String): Usuario? {
        val usuario: Usuario
        val sql = "SELECT * FROM $TABLA_USUARIOS WHERE login='$login' AND contra='$contra'"
        val cursor = mDB.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            usuario = Usuario(
                login = cursor.getString(cursor.getColumnIndexOrThrow("login")),
                contra = cursor.getString(cursor.getColumnIndexOrThrow("contra")),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
            )
        } else {
            return null
        }
        return usuario
    }

    fun getAllCategorias(): MutableList<Categoria> {
        val lista: MutableList<Categoria> = ArrayList()
        val cursor: Cursor = mDB.query(
            TABLA_CATEGORIAS,
            null, null,
            null, null,
            null, null
        )
        if (cursor.moveToFirst()) {
            do {
                lista.add(
                    Categoria(
                        nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    )
                )

            } while (cursor.moveToNext())

        }
        if (!cursor.isClosed)
            cursor.close()
        return lista
    }


}



