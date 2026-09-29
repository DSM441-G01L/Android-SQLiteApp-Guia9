package com.example.sqliteapp.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.sqliteapp.db.HelperDB

class Usuario(context: Context) {
    private val helper: HelperDB = HelperDB(context)
    private val db: SQLiteDatabase = helper.writableDatabase

    fun registrarUsuario(nick: String, pass: String): Long {
        val cv = ContentValues().apply {
            put(HelperDB.KEY_USUARIO_NICK, nick)
            put(HelperDB.KEY_USUARIO_PASSWORD, pass)
        }
        return db.insert(HelperDB.TABLE_USUARIO, null, cv)
    }

    fun validarLogin(nick: String, pass: String): Boolean {
        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM ${HelperDB.TABLE_USUARIO} WHERE ${HelperDB.KEY_USUARIO_NICK} = ? AND ${HelperDB.KEY_USUARIO_PASSWORD} = ?",
            arrayOf(nick, pass)
        )
        val existe = cursor.count > 0
        cursor.close()
        return existe
    }
}