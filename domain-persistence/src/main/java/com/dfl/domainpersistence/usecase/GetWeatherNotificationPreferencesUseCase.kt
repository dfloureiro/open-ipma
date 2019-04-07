package com.dfl.domainpersistence.usecase

import com.dfl.domainpersistence.repository.PersistenceRepository

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

    fun setAnalyticsStatus(status: Boolean) {
        preferencesRepository.putBoolean(ANALYTICS_STATUS_KEY, status)
    }

    fun getPrivacyPolicyDialogShowed(): Boolean {
        return preferencesRepository.getBoolean(PRIVACY_POLICY_DIALOG_KEY, PRIVACY_POLICY_DIALOG_DEFAULT_VALUE)
    }

    fun setPrivacyPolicyDialogShowed(wasShowed: Boolean) {
        preferencesRepository.putBoolean(PRIVACY_POLICY_DIALOG_KEY, wasShowed)
    }

    companion object {
        const val WEATHER_NOTIFICATION_KEY = "weather_notification_preference"
        private const val WEATHER_NOTIFICATION_DEFAULT_VALUE = true
        const val NOTIFICATION_TIME_KEY = "notification_time_preference"
        private const val NOTIFICATION_TIME_DEFAULT_VALUE = "8"
        const val ANALYTICS_STATUS_KEY = "analytics_status"
        private const val ANALYTICS_STATUS_DEFAULT_VALUE = false
        const val PRIVACY_POLICY_DIALOG_KEY = "privacy_policy_dialog"
        private const val PRIVACY_POLICY_DIALOG_DEFAULT_VALUE = false
    }
}