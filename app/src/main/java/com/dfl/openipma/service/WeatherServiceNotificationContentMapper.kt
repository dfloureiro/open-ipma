package com.dfl.openipma.service

import com.dfl.domainipma.model.City
import com.dfl.domainipma.model.CityForecast
import com.dfl.domainipma.model.WeatherType
import com.dfl.openipma.base.BaseUiModelMapper
import dagger.Reusable
import javax.inject.Inject

@Reusable
class WeatherServiceNotificationContentMapper @Inject constructor() : BaseUiModelMapper() {

    fun create(
        cityId: Int,
        todayForecast: CityForecast,
        cities: List<City>,
        weatherTypes: List<WeatherType>
    ): NotificationContent {
        val cityName = cities.find { it.id == cityId }?.name ?: defaultUnknownDescription
        val text = weatherTypes.find { it.weatherTypeId == todayForecast.weatherType }?.weatherTypeDescription
            ?: defaultUnknownDescription

        return NotificationContent(
            "${todayForecast.maxTemp}º",
            "${todayForecast.minTemp}º",
            cityName,
            text,
            getIcon(todayForecast.weatherType)
        )
    }
}