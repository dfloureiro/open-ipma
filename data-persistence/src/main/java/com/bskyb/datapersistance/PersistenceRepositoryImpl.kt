package com.bskyb.datapersistance

import android.content.SharedPreferences
import com.bskyb.domainpersistence.repository.PersistenceRepository

class PersistenceRepositoryImpl(private val sharedPreferences: SharedPreferences) : PersistenceRepository {

    override fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }
}