package com.dfl.openipma

import android.util.Log
import com.dfl.domainipma.model.City
import com.dfl.domainipma.model.Forecast
import com.dfl.domainipma.model.WeatherType
import com.dfl.domainipma.model.WindSpeed
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastUiModelCreator @Inject constructor() : BaseUiModelMapper() {

    fun create(
        forecasts: List<Forecast>,
        windSpeeds: List<WindSpeed>,
        weathersType: List<WeatherType>,
        cities: List<City>
    ): List<ForecastUiModel> {
        val forecastUiModels = mutableListOf<ForecastUiModel>()
        forecasts.forEach { forecast ->
            val city = cities.find { it.id == forecast.cityId }
            val weatherDescription =
                weathersType.find { it.weatherTypeId == forecast.weatherType }?.weatherTypeDescription ?: "Unknown"
            val windSpeedDescription =
                windSpeeds.find { it.windSpeedId == forecast.windSpeed }?.windSpeedDescription ?: "Unknown"
            when {
                city != null ->
                    forecastUiModels.add(
                        ForecastUiModel(
                            city.id,
                            city.name,
                            forecast.minTemp,
                            forecast.maxTemp,
                            forecast.precipitation.substringBefore("."),
                            windSpeedDescription,
                            forecast.windDirection.rotation,
                            weatherDescription,
                            getIcon(forecast.weatherType),
                            getBackgroundColor(forecast.weatherType)
                        )
                    )
                else -> Log.e("ups", "the city id ${forecast.cityId} does not have a valid forecast")
            }
        }
        return forecastUiModels
    }
}