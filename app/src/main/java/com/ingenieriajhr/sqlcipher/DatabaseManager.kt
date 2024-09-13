package com.ingenieriajhr.sqlcipher
import android.content.ContentValues
import android.content.Context
import net.sqlcipher.database.SQLiteDatabase

class DatabaseManager(private val context: Context) {

    private val databaseHelper = EncryptedDatabaseHelper(context)

    fun getReadableDatabase(password: String): SQLiteDatabase {
        return SQLiteDatabase.openOrCreateDatabase(
            context.getDatabasePath(EncryptedDatabaseHelper.DATABASE_NAME),
            password,
            null
        )
    }

    fun getWritableDatabase(password: String): SQLiteDatabase {
        return SQLiteDatabase.openOrCreateDatabase(
            context.getDatabasePath(EncryptedDatabaseHelper.DATABASE_NAME),
            password,
            null
        )
    }

    // Eliminar la base de datos
    fun deleteDatabase() {
        val databaseFile = context.getDatabasePath(EncryptedDatabaseHelper.DATABASE_NAME)
        if (databaseFile.exists()) {
            if (databaseFile.delete()) {
                println("Database deleted successfully.")
            } else {
                println("Failed to delete database.")
            }
        }
    }

    // Crear la base de datos de nuevo
    fun recreateDatabase() {
        deleteDatabase() // Primero eliminar la base de datos
        // Crear una nueva base de datos
        val db = getWritableDatabase(password = "")
        db.close()
    }


    // Consultar un registro por ID
    fun queryDataById(id: Int): String? {
        val db = getWritableDatabase(password = "")
        val cursor = db.query(
            "users",
            arrayOf("name"),
            "id = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        val name = if (cursor.moveToFirst()) {
            cursor.getString(cursor.getColumnIndexOrThrow("name"))
        } else {
            null
        }
        cursor.close()
        db.close()
        return name
    }

    // Actualizar un registro
    fun updateData(id: Int, newName: String) {
        val db = getWritableDatabase(password = "")
        val values = ContentValues().apply {
            put("name", newName)
        }
        db.update("users", values, "id = ?", arrayOf(id.toString()))
        db.close()
    }

    // Eliminar un registro
    fun deleteData(id: Int) {
        val db = getWritableDatabase(password = "")
        db.delete("users", "id = ?", arrayOf(id.toString()))
        db.close()
    }

}
