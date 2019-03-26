package com.bskyb.domainpersistence.usecase

import com.bskyb.domainpersistence.repository.PersistenceRepository

class GetWeatherNotificationPreferencesUseCase(private val preferencesRepository: PersistenceRepository) {

    fun isWeatherNotificationEnabled(): Boolean {
        return preferencesRepository.getBoolean(WEATHER_NOTIFICATION_KEY, WEATHER_NOTIFICATION_DEFAULT_VALUE)
    }

    fun getHourForWeatherNotification(): Int {
        return preferencesRepository.getString(NOTIFICATION_TIME_KEY, NOTIFICATION_TIME_DEFAULT_VALUE)?.toInt()
            ?: NOTIFICATION_TIME_DEFAULT_VALUE.toInt()
    }

    fun getAnalyticsStatus(): Boolean {
        return preferencesRepository.getBoolean(ANALYTICS_STATUS_KEY, ANALYTICS_STATUS_DEFAULT_VALUE)
    }

    companion object {
        const val WEATHER_NOTIFICATION_KEY = "weather_notification_preference"
        private const val WEATHER_NOTIFICATION_DEFAULT_VALUE = true
        const val NOTIFICATION_TIME_KEY = "notification_time_preference"
        private const val NOTIFICATION_TIME_DEFAULT_VALUE = "8"
        const val ANALYTICS_STATUS_KEY = "analytics_status"
        private const val ANALYTICS_STATUS_DEFAULT_VALUE = true
    }
}