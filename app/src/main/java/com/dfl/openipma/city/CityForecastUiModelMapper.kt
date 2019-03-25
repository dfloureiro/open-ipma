package com.dfl.openipma.city

import com.dfl.domainipma.model.CityForecast
import com.dfl.domainipma.model.WeatherType
import com.dfl.domainipma.model.WindSpeed
import com.dfl.openipma.base.BaseUiModelMapper
import dagger.Reusable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@Reusable
class CityForecastUiModelMapper @Inject constructor() : BaseUiModelMapper() {

    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.forLanguageTag("pt"))

    fun map(
        forecasts: List<CityForecast>,
        windSpeeds: List<WindSpeed>,
        weathersType: List<WeatherType>
    ): List<CityForecastUiModel> {
        val cityForecastsUiModels = mutableListOf<CityForecastUiModel>()
        forecasts.forEach { forecast ->
            val forecastDate = GregorianCalendar()
            forecastDate.time = simpleDateFormat.parse(forecast.forecastDate)
            val weatherDescription =
                weathersType.find { it.weatherTypeId == forecast.weatherType }?.weatherTypeDescription
                    ?: defaultUnknownDescription
            val windSpeedDescription =
                windSpeeds.find { it.windSpeedId == forecast.windSpeed }?.windSpeedDescription
                    ?: defaultUnknownDescription
            cityForecastsUiModels.add(
                CityForecastUiModel(
                    forecastDate,
                    setTemperatureSuffix(forecast.minTemp),
                    setTemperatureSuffix(forecast.maxTemp),
                    setPrecipitationSuffix(forecast.precipitation.substringBefore(".")),
                    windSpeedDescription,
                    forecast.windDirection.rotation,
                    forecast.windDirection.name,
                    weatherDescription,
                    getIcon(forecast.weatherType),
                    getBackgroundColor(forecast.weatherType),
                    forecast.isToday,
                    getTemperatureStatusDescription(forecast.temperatureStatus)
                )
            )
        }
        return cityForecastsUiModels
    }
}