package com.ingenieriajhr.sqlcipher

import android.content.Context
import android.content.SharedPreferences

class KeyManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    fun saveKey(key: String) {
        sharedPreferences.edit().putString("encryption_key", key).apply()
    }

    fun getKey(): String? {
        return sharedPreferences.getString("encryption_key", null)
    }

    fun deleteKey() {
        sharedPreferences.edit().remove("encryption_key").apply()
    }
}
