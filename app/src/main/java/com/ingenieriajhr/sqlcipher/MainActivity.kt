package com.ingenieriajhr.sqlcipher

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.sqlcipher.database.SQLiteDatabase

class MainActivity : AppCompatActivity() {

    private val databasePassword = "your_secure_password"
    private lateinit var dbManager: DatabaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa el DatabaseManager
        dbManager = DatabaseManager(this)
        // Abre la base de datos
        val db: SQLiteDatabase = dbManager.getWritableDatabase(databasePassword)

        // Aquí puedes comenzar a interactuar con la base de datos
        // Ejemplo: insertar un registro
        val contentValues = ContentValues().apply {
            put("name", "Test Name")
        }
        db.insert("users", null, contentValues)
        db.close()


        // Eliminar y recrear la base de datos
        dbManager.recreateDatabase()
    }
}