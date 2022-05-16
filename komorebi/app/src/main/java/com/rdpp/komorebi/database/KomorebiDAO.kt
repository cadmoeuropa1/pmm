package com.rdpp.komorebi.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.rdpp.komorebi.model.Article
import java.util.*

class KomorebiDAO(context: Context) {

    private val database: SQLiteDatabase

    companion object {
        const val DATABASE_NAME = "Komorebi"
        const val DATABASE_VERSION = 1
        const val TABLE_USERS = "users"
        const val TABLE_ARTICLES = "articles"
        const val TABLE_WORKSHOPS = "workshops"
        const val TABLE_FORUM = "forum"
    }

    init {
        val structure = KomorebiDB(context, DATABASE_NAME, null, DATABASE_VERSION)
        database = structure.writableDatabase

    }

    fun insertExamples() {
        insertArticle(
            UUID.randomUUID().toString(),
            "¿Estigmatizan las bajas por reglas dolorosas? Los laboralistas dudan de su \"utilidad\" porque ya existen bajas por dolor incapacitante",
            "Discriminación, estigmatización, arma de doble filo... el debate está servido en un nuevo asunto que aborda la salud de las mujeres. El Ministerio de Igualdad llevará el próximo martes al Consejo de Ministros la futura ley del aborto que incluye, entre otras medidas, la posibilidad de que las mujeres que sufren reglas muy dolorosas e incapacitantes puedan cogerse bajas laborales. \n" +
                    "\n" +
                    "Varios ministerios implicados se vieron sorprendidos por el anuncio. Desde el departamento de Irene Montero defienden que, en palabras de la delegada del Gobierno contra la Violencia de Género, Victoria Rosell, \"cuando la administración pública asume esta prestación, los empresarios no pueden alegar que resultaría discriminatorio\", pero la vicepresidenta primera y ministra de Economía, Nadia Calviño, fue meridiana al afirmar que la medida podría resultar \"en una estigmatización de las mujeres\". Una idea que rechaza la vicepresidenta segunda y ministra de Trabajo, Yolanda Díaz, que se ha declarado a favor de la medida.",
            "16/05/2022",
            "https://www.20minutos.es/noticia/4999486/0/debate-sobre-las-bajas-por-reglas-dolorosas-es-un-paso-en-la-buena-direccion/"
        )
        insertArticle(
            UUID.randomUUID().toString(),
            "El Supremo rechaza la atenuante de confesión y confirma la condena a un hombre por asesinar a su exmujer",
            "El Tribunal Supremo ha desestimado el recurso de casación de un hombre condenado en 2021 a 22 años y medio de cárcel por el asesinato de su expareja la noche del 5 de julio de 2018 en Madrid, y que solicitaba a la justicia un castigo menor por el hecho de haber confesado el crimen al escribir una nota autoinculpatoria.\n" +
                    "\n" +
                    "Antes de matarla, Noel B. había sometido a la víctima a una \"situación de alienación continuada\" a lo largo de \"todo\" el noviazgo, que duró dos años y estuvo marcado por una \"violencia psíquica\" que dio lugar a una \"relación de desigualdad basada en una posición de intolerable dominación, de sometimiento al imperio del victimario\", según señala la Sala de lo Penal en una sentencia del pasado 28 de abril.\n" +
                    "\n" +
                    "Los magistrados han confirmado la condena acordada por la Audiencia de Madrid, que decretó para el autor del crimen 20 años de cárcel por el delito de asesinato con la agravante de parentesco, un año y nueve meses por maltrato habitual y nueve meses más por el delito continuado de quebrantamiento de la orden de alejamiento.",
            "14/05/2022",
            "https://www.20minutos.es/noticia/5000016/0/supremo-rechaza-la-atenuante-de-confesion-confirma-la-condena-a-hombre-por-asesinar-a-su-exmujer/"
        )
        insertArticle(
            UUID.randomUUID().toString(),
            "El Gobierno confirma un asesinato por violencia machista en Logroño de 2020",
            "La Delegación del Gobierno contra la Violencia de Género confirma el caso de asesinato por violencia machista de una mujer española de 56 años en Logroño el 13 de octubre de 2020 tras el resultado delas pruebas forenses y su instrucción como homicidio por violencia de género. El caso estaba hasta ahora en investigación.\n" +
                    "\n" +
                    "La víctima fue presuntamente asesinada por su cónyuge, español de 56 años. No tenía hijos ni hijas menores de edad y no existían denuncias previas por violencia de género. \n" +
                    "\n" +
                    "Este caso se consideró inicialmente como un suicidio, pero tras la investigación llevada a cabo se ha confirmado como crimen machista.",
            "13/5/2022",
            "https://www.20minutos.es/noticia/4999670/0/el-gobierno-confirma-un-asesinato-por-violencia-machista-en-logrono-de-2020/"
        )
        insertUser(UUID.randomUUID().toString(), "userP", "user", "https://tomaminabo.com/jpg", 0)
        insertUser(UUID.randomUUID().toString(), "usuaria", "alo", "https://tomaminabo.com/jpg", 1)
        insertUser(UUID.randomUUID().toString(), "user", "pitopi", "https://tomaminabo.com/jpg", 1)
    }

    fun insertArticle(id: String, name: String, article: String, date: String, url: String): Int {
        val values = ContentValues()
        values.put("article_Id", id)
        values.put("title", name)
        values.put("article", article)
        values.put("date", date)
        values.put("url", url)

        return database.insert(TABLE_ARTICLES, null, values).toInt()
    }

    fun insertUser(
        id: String,
        user: String,
        password: String,
        profilePicture: String,
        uType: Int
    ): Int {
        val values = ContentValues()
        values.put("user_Id", id)
        values.put("user", user)
        values.put("password", password)
        values.put("profilePicture", profilePicture)
        values.put("userType", uType)

        return database.insert(TABLE_USERS, null, values).toInt()
    }

    fun getAllArticles(): MutableList<Article> {
        val articles: MutableList<Article> = ArrayList()
        val sql = "SELECT * FROM $TABLE_ARTICLES"
        val cursor: Cursor = database.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                articles.add(
                    Article(
                        cursor.getString(cursor.getColumnIndexOrThrow("article_Id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("article")),
                        cursor.getString(cursor.getColumnIndexOrThrow("date")),
                        cursor.getString(cursor.getColumnIndexOrThrow("url"))
                    )
                )
            } while (cursor.moveToNext())

        }
        if (cursor.isClosed) {
            cursor.close()
        }
        return articles
    }
}