package com.dfl.domainpersistence.repository

interface PersistenceRepository {

    fun putBoolean(key: String, value: Boolean)

    fun getBoolean(key: String, defaultValue: Boolean): Boolean

    fun putString(key: String, value: String)

    fun getString(key: String, defaultValue: String?): String?

    fun putInt(key: String, value: Int)

    fun getInt(key: String, defaultValue: Int): Int

    fun putStringSet(key: String, value: Set<String>)

    fun getStringSet(key: String, defaultValue: Set<String>?): MutableSet<String>?
}