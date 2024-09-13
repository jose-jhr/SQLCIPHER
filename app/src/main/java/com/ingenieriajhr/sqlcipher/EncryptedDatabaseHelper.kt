package com.ingenieriajhr.sqlcipher

import android.content.Context
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteOpenHelper

class EncryptedDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    init {
        // Inicializar SQLCipher
        SQLiteDatabase.loadLibs(context)
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Crear tablas
        val CREATE_TABLE = ("CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT)")
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Actualizar la base de datos
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "encrypted_database.db"
        private const val DATABASE_VERSION = 1
    }
}
