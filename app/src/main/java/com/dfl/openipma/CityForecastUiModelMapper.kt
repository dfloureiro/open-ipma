package com.dfl.openipma

import android.util.Log
import com.dfl.domainipma.model.CityForecast
import com.dfl.domainipma.model.Forecast
import com.dfl.domainipma.model.WeatherType
import com.dfl.domainipma.model.WindSpeed
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CityForecastUiModelMapper @Inject constructor() : BaseUiModelMapper() {

    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.forLanguageTag("pt"))

    fun map(
        cityId: Int,
        forecasts: List<CityForecast>,
        windSpeeds: List<WindSpeed>,
        weathersType: List<WeatherType>
    ): List<CityForecastUiModel> {
        val cityForecastsUiModels = mutableListOf<CityForecastUiModel>()
        forecasts.forEach { forecast ->
            val forecastDate = GregorianCalendar()
            forecastDate.time = simpleDateFormat.parse(forecast.forecastDate)
            val weatherDescription = weathersType.find { it.weatherTypeId == forecast.weatherType }?.weatherTypeDescription
            val windSpeedDescription = windSpeeds.find { it.windSpeedId == forecast.windSpeed }?.windSpeedDescription
            when {
                weatherDescription != null && windSpeedDescription != null -> {
                    cityForecastsUiModels.add(
                        CityForecastUiModel(
                            forecastDate,
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
                }
                else -> Log.e("ups", "the city id $cityId does not have a valid forecast on date $forecastDate")
            }
        }
        return cityForecastsUiModels
    }
}