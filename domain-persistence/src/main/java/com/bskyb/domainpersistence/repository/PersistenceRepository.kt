package com.bskyb.domainpersistence.repository

interface PersistenceRepository {

    fun putBoolean(key: String, value: Boolean)

    fun getBoolean(key: String, defaultValue: Boolean): Boolean
}