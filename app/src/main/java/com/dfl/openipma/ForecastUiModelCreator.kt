package com.dfl.openipma

import android.location.Location
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
        cities: List<City>,
        currentLocation: Location?
    ): List<ForecastUiModel> {
        val forecastUiModels = mutableListOf<ForecastUiModel>()
        forecasts.forEach { forecast ->
            val city = cities.find { it.id == forecast.cityId }
            val weatherDescription =
                weathersType.find { it.weatherTypeId == forecast.weatherType }?.weatherTypeDescription
                    ?: defaultUnknownDescription
            val windSpeedDescription =
                windSpeeds.find { it.windSpeedId == forecast.windSpeed }?.windSpeedDescription ?: defaultUnknownDescription
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
        return when {
            currentLocation != null -> {
                val closestCityId = closestCity(cities, currentLocation)
                moveClosestCityToFirstPosition(closestCityId, forecastUiModels)
            }
            else -> forecastUiModels
        }
    }

    private fun moveClosestCityToFirstPosition(
        closestCityId: Int,
        forecastUiModels: List<ForecastUiModel>
    ): List<ForecastUiModel> {
        //TODO move this to correct place
        return forecastUiModels.toMutableList()
            .let { mutableForecastUiModel ->
                forecastUiModels.find { it.cityId == closestCityId }
                    ?.also { forecastUiModel ->
                        mutableForecastUiModel.remove(forecastUiModel)
                        mutableForecastUiModel.add(0, forecastUiModel)
                    }
                mutableForecastUiModel
            }
    }

    private fun closestCity(cities: List<City>, location: Location): Int {
        //TODO move this to correct place
        var closestDistance: Float = -1F
        var closestCity: City = cities.first()
        for (city in cities) {
            val distance = FloatArray(1)
            Location.distanceBetween(
                location.latitude,
                location.longitude,
                city.latitude,
                city.longitude,
                distance
            )
            if (closestDistance == -1F || closestDistance > distance[0]) {
                closestDistance = distance[0]
                closestCity = city
            }
        }
        return closestCity.id
    }
}