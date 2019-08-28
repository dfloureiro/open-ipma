package com.dfl.datapersistance

import android.content.SharedPreferences
import com.dfl.domainpersistence.repository.PersistenceRepository

class PersistenceRepositoryImpl(private val sharedPreferences: SharedPreferences) :
    PersistenceRepository {

    override fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    override fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String, defaultValue: String?): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    override fun putInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    override fun putStringSet(key: String, value: Set<String>) {
        sharedPreferences.edit().putStringSet(key, value).apply()
    }

    override fun getStringSet(key: String, defaultValue: Set<String>?): MutableSet<String>? {
        return sharedPreferences.getStringSet(key, defaultValue)
    }
}