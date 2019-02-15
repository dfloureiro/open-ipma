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

    companion object {
        const val WEATHER_NOTIFICATION_KEY = "weather_notification_preference"
        private const val WEATHER_NOTIFICATION_DEFAULT_VALUE = true
        private const val NOTIFICATION_TIME_KEY = "notification_time_preference"
        private const val NOTIFICATION_TIME_DEFAULT_VALUE = "8"
    }
}