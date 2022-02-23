package com.rdpp.fragmentPaniti.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.rdpp.fragmentPaniti.dataclass.Event
import com.rdpp.fragmentPaniti.dataclass.User

class ProgrammersDAO(context: Context) {


    private val database: SQLiteDatabase

    companion object {
        const val DATABASE_NAME = "Programmers"
        const val DATABASE_VERSION = 1
        const val TABLE_EVENTS = "Events"
        const val TABLE_USER_EVENT = "UserEvents"
        const val TABLE_USER = "User"
    }

    init {
        val structure = ProgrammersDB(context, DATABASE_NAME, null, DATABASE_VERSION)
        database = structure.writableDatabase
    }


    fun addUser(user: User) {
        val values = ContentValues()
        values.put("login", user.login)
        values.put("pass", user.pass)
        values.put("type", user.type)
        database.insert(TABLE_USER, null, values)
    }

    fun getUser(login: String, password: String): User? {
        var user: User? = null
        val sql = "SELECT * from $TABLE_USER WHERE login='$login' AND pass='$password'"
        val cursor: Cursor = database.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            user = User(
                cursor.getInt(cursor.getColumnIndexOrThrow("user_Id")),
                cursor.getString(cursor.getColumnIndexOrThrow("login")),
                cursor.getString(cursor.getColumnIndexOrThrow("pass")),
                cursor.getString(cursor.getColumnIndexOrThrow("type"))
            )
        }
        cursor.close()
        return user
    }

    fun getAllEvents(): MutableList<Event> {
        val events: MutableList<Event> = ArrayList()
        val cursor: Cursor = database.query(
            TABLE_EVENTS,
            null, null,
            null, null,
            null, null
        )
        if (cursor.moveToFirst()) {
            do {
                events.add(
                    Event(
                        cursor.getInt(0),
                        cursor.getString(cursor.getColumnIndexOrThrow("date")),
                        cursor.getString(cursor.getColumnIndexOrThrow("title")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    )
                )
            } while (cursor.moveToNext())

        }
        if (!cursor.isClosed)
            cursor.close()
        return events
    }

    fun addEvent(event: Event) {
        val values = ContentValues()
        values.put("date", event.date)
        values.put("title", event.title)
        values.put("description", event.description)
        database.insert(TABLE_EVENTS, null, values)
    }

    fun getUser(user_Id: Int?): User? {
        var user: User? = null
        val sql = "SELECT * from $TABLE_USER WHERE user_Id ='$user_Id'"
        val cursor: Cursor = database.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            user = User(
                cursor.getInt(cursor.getColumnIndexOrThrow("user_Id")),
                cursor.getString(cursor.getColumnIndexOrThrow("login")),
                cursor.getString(cursor.getColumnIndexOrThrow("pass")),
                cursor.getString(cursor.getColumnIndexOrThrow("type"))
            )
            return user
        }
        cursor.close()
        return user
    }

    fun getEvents(date: String): MutableList<Event> {
        val events: MutableList<Event> = ArrayList()
        val sql = "SELECT * FROM Events WHERE date > '$date'"
        val cursor: Cursor = database.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                events.add(
                    Event(
                        cursor.getInt(0),
                        cursor.getString(cursor.getColumnIndexOrThrow("date")),
                        cursor.getString(cursor.getColumnIndexOrThrow("title")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    )
                )
            } while (cursor.moveToNext())

        }
        if (!cursor.isClosed)
            cursor.close()
        return events
    }
}

