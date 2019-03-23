package com.dfl.domainanalytics.usecase

import com.dfl.domainanalytics.repository.AnalyticsRepository

class HandleOnSettingsChangeEvents(private val analyticsRepository: AnalyticsRepository) {

    fun logOnNotificationTimeChange(time: String) {
        analyticsRepository.logEvent("settings_notification_time", mapOf(Pair("notification_hour", time)))
    }

    fun logOnNotificationStatusChange(isEnabled: Boolean) {
        analyticsRepository.logEvent(
            "settings_notification_status",
            mapOf(Pair("notification_enabled", isEnabled.toString()))
        )
    }
}