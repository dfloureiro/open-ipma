package com.dfl.domainpersistence.usecase

import com.dfl.domainpersistence.ANALYTICS_STATUS_DEFAULT_VALUE
import com.dfl.domainpersistence.ANALYTICS_STATUS_KEY
import com.dfl.domainpersistence.NOTIFICATION_TIME_DEFAULT_VALUE
import com.dfl.domainpersistence.NOTIFICATION_TIME_KEY
import com.dfl.domainpersistence.PRIVACY_POLICY_DIALOG_DEFAULT_VALUE
import com.dfl.domainpersistence.PRIVACY_POLICY_DIALOG_KEY
import com.dfl.domainpersistence.WEATHER_NOTIFICATION_DEFAULT_VALUE
import com.dfl.domainpersistence.WEATHER_NOTIFICATION_KEY
import com.dfl.domainpersistence.repository.PersistenceRepository

class GetWeatherNotificationPreferencesUseCase(private val preferencesRepository: PersistenceRepository) {

    fun isWeatherNotificationEnabled(): Boolean {
        return preferencesRepository.getBoolean(
            WEATHER_NOTIFICATION_KEY,
            WEATHER_NOTIFICATION_DEFAULT_VALUE
        )
    }

    fun getHourForWeatherNotification(): Int {
        return preferencesRepository.getString(
            NOTIFICATION_TIME_KEY,
            NOTIFICATION_TIME_DEFAULT_VALUE
        )?.toInt()
            ?: NOTIFICATION_TIME_DEFAULT_VALUE.toInt()
    }

    fun getAnalyticsStatus(): Boolean {
        return preferencesRepository.getBoolean(
            ANALYTICS_STATUS_KEY,
            ANALYTICS_STATUS_DEFAULT_VALUE
        )
    }

    fun setAnalyticsStatus(status: Boolean) {
        preferencesRepository.putBoolean(ANALYTICS_STATUS_KEY, status)
    }

    fun getPrivacyPolicyDialogShowed(): Boolean {
        return preferencesRepository.getBoolean(
            PRIVACY_POLICY_DIALOG_KEY,
            PRIVACY_POLICY_DIALOG_DEFAULT_VALUE
        )
    }

    fun setPrivacyPolicyDialogShowed(wasShowed: Boolean) {
        preferencesRepository.putBoolean(PRIVACY_POLICY_DIALOG_KEY, wasShowed)
    }
}
