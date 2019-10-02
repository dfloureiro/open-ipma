package com.dfl.domainanalytics.usecase

import com.dfl.domainanalytics.CITY_FORECASTS_OPEN_KEY
import com.dfl.domainanalytics.CITY_ID
import com.dfl.domainanalytics.HOME_OPEN_KEY
import com.dfl.domainanalytics.SEISMIC_OPEN_KEY
import com.dfl.domainanalytics.SETTINGS_OPEN_KEY
import com.dfl.domainanalytics.repository.AnalyticsRepository

class HandleOnScreenOpenEvents(private val analyticsRepository: AnalyticsRepository) {

    fun logCityForecastsScreenLaunch(cityId: Int) {
        analyticsRepository.logEvent(
            CITY_FORECASTS_OPEN_KEY,
            mapOf(Pair(CITY_ID, cityId.toString()))
        )
    }

    fun logHomeScreenLaunch() {
        analyticsRepository.logEvent(HOME_OPEN_KEY, mapOf())
    }

    fun logSettingsScreenLaunch() {
        analyticsRepository.logEvent(SETTINGS_OPEN_KEY, mapOf())
    }

    fun logSeismicScreenLaunch() {
        analyticsRepository.logEvent(SEISMIC_OPEN_KEY, mapOf())
    }
}
