package com.dfl.domainanalytics.usecase

import com.dfl.domainanalytics.repository.AnalyticsRepository

class HandleOnScreenOpenEvents(private val analyticsRepository: AnalyticsRepository) {

    fun logCityForecastsScreenLaunch(cityId: Int) {
        analyticsRepository.logEvent("city_forecasts_open", mapOf(Pair("city_id", cityId.toString())))
    }

    fun logHomeScreenLaunch() {
        analyticsRepository.logEvent("home_open", mapOf())
    }

    fun logSettingsScreenLaunch() {
        analyticsRepository.logEvent("settings_open", mapOf())
    }
}