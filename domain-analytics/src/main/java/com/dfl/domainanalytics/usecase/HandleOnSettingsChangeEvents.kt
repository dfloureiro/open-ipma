package com.dfl.domainanalytics.usecase

import com.dfl.domainanalytics.NOTIFICATION_ENABLED
import com.dfl.domainanalytics.NOTIFICATION_HOUR
import com.dfl.domainanalytics.SETTINGS_NOTIFICATION_STATUS
import com.dfl.domainanalytics.SETTINGS_NOTIFICATION_TIME_KEY
import com.dfl.domainanalytics.repository.AnalyticsRepository

class HandleOnSettingsChangeEvents(private val analyticsRepository: AnalyticsRepository) {

    fun logOnNotificationTimeChange(time: String) {
        analyticsRepository.logEvent(SETTINGS_NOTIFICATION_TIME_KEY, mapOf(Pair(NOTIFICATION_HOUR, time)))
    }

    fun logOnNotificationStatusChange(isEnabled: Boolean) {
        analyticsRepository.logEvent(
            SETTINGS_NOTIFICATION_STATUS,
            mapOf(Pair(NOTIFICATION_ENABLED, isEnabled.toString()))
        )
    }

    fun setAnalyticsStatus(status: Boolean) {
        analyticsRepository.setStatus(status)
    }
}