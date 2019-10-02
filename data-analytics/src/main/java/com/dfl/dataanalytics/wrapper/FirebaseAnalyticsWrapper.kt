package com.dfl.dataanalytics.wrapper

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalyticsWrapper(private val firebaseAnalytics: FirebaseAnalytics) {

    fun setStatus(status: Boolean) {
        firebaseAnalytics.setAnalyticsCollectionEnabled(status)
    }

    fun setUserProperty(name: String, value: String) {
        firebaseAnalytics.setUserProperty(name, value)
    }

    fun logEvent(name: String, params: Bundle) {
        firebaseAnalytics.logEvent(name, params)
    }
}
