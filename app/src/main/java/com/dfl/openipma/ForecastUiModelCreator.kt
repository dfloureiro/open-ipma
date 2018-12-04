package com.dfl.openipma

import android.util.Log
import com.dfl.domainipma.model.City
import com.dfl.domainipma.model.Forecast
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastUiModelCreator @Inject constructor() {

    fun create(forecasts: List<Forecast>, cities: List<City>): List<ForecastUiModel> {
        val forecastUiModels = mutableListOf<ForecastUiModel>()
        forecasts.forEach { forecast ->
            val city = cities.find { it.id == forecast.cityId }
            when {
                city != null -> forecastUiModels.add(
                    ForecastUiModel(
                        city.id,
                        city.name,
                        forecast.minTemp,
                        forecast.maxTemp,
                        forecast.precipitation,
                        forecast.windDirection.rotation
                    )
                )
                else -> Log.e("ups", "the city id ${forecast.cityId} does not exist in cities")
            }
        }
        return forecastUiModels
    }
}