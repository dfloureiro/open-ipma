package com.dfl.domainanalytics.repository

interface AnalyticsRepository {

    fun setStatus(status: Boolean)

    fun logEvent(name: String, properties: Map<String, String>)

    fun setUserProperty(name: String, value: String)
}
