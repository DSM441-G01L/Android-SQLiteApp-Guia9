package com.example.sqliteapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HelperDB(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "tienda.sqlite"
        private const val DB_VERSION = 2 // Cambiamos a versión 2 para crear la nueva tabla

        // Tablas existentes
        const val TABLE_CATEGORIA = "categoria"
        const val TABLE_PRODUCTOS = "productos"

        // Nueva Tabla Usuario
        const val TABLE_USUARIO = "usuario"
        const val KEY_USUARIO_NICK = "nick"
        const val KEY_USUARIO_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Tablas originales de la guía
        db.execSQL("CREATE TABLE $TABLE_CATEGORIA (idcategoria INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT);")
        db.execSQL("CREATE TABLE $TABLE_PRODUCTOS (idproducto INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, precio REAL, idcategoria INTEGER);")

        // Nueva Tabla de Usuarios (Discusión de Resultados 3.b)
        db.execSQL("CREATE TABLE $TABLE_USUARIO ($KEY_USUARIO_NICK TEXT PRIMARY KEY, $KEY_USUARIO_PASSWORD TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CATEGORIA")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIO")
        onCreate(db)
    }
}