package com.dfl.dataanalytics

import android.os.Bundle
import com.dfl.dataanalytics.wrapper.FirebaseAnalyticsWrapper
import com.dfl.domainanalytics.repository.AnalyticsRepository

class AnalyticsRepositoryImpl(private val firebaseAnalyticsWrapper: FirebaseAnalyticsWrapper) : AnalyticsRepository {

    override fun setStatus(status: Boolean) {
        firebaseAnalyticsWrapper.setStatus(status)
    }

    override fun logEvent(name: String, properties: Map<String, String>) {
        val bundle = Bundle()
        for (property in properties) {
            bundle.putString(property.key, property.value)
        }
        firebaseAnalyticsWrapper.logEvent(name, bundle)
    }

    override fun setUserProperty(name: String, value: String) {
        firebaseAnalyticsWrapper.setUserProperty(name, value)
    }
}
