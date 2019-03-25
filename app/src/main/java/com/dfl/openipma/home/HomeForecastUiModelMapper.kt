package com.dfl.openipma.home

import android.util.Log
import com.dfl.domainipma.model.City
import com.dfl.domainipma.model.Forecast
import com.dfl.domainipma.model.WeatherType
import com.dfl.domainipma.model.WindSpeed
import com.dfl.openipma.base.BaseUiModelMapper
import dagger.Reusable
import javax.inject.Inject

@Reusable
class HomeForecastUiModelMapper @Inject constructor() : BaseUiModelMapper() {

    fun create(
        forecasts: List<Forecast>,
        windSpeeds: List<WindSpeed>,
        weathersType: List<WeatherType>,
        cities: List<City>
    ): List<HomeForecastUiModel> {
        val forecastUiModels = mutableListOf<HomeForecastUiModel>()
        forecasts.forEach { forecast ->
            val city = cities.find { it.id == forecast.cityId }
            val weatherDescription =
                weathersType.find { it.weatherTypeId == forecast.weatherType }?.weatherTypeDescription
                    ?: defaultUnknownDescription
            val windSpeedDescription =
                windSpeeds.find { it.windSpeedId == forecast.windSpeed }?.windSpeedDescription
                    ?: defaultUnknownDescription
            when {
                city != null ->
                    forecastUiModels.add(
                        HomeForecastUiModel(
                            city.id,
                            city.name,
                            setTemperatureSuffix(forecast.minTemp),
                            setTemperatureSuffix(forecast.maxTemp),
                            setPrecipitationSuffix(forecast.precipitation.substringBefore(".")),
                            windSpeedDescription,
                            forecast.windDirection.rotation,
                            weatherDescription,
                            getIcon(forecast.weatherType),
                            getBackgroundColor(forecast.weatherType),
                            forecast.isClosestCity
                        )
                    )
                else -> Log.e("ups", "the city id ${forecast.cityId} does not have a valid forecast")
            }
        }
        return forecastUiModels
    }
}