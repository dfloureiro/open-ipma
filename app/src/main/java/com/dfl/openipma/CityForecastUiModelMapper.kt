package com.dfl.openipma

import android.text.format.DateUtils
import com.dfl.domainipma.model.CityForecast
import com.dfl.domainipma.model.WeatherType
import com.dfl.domainipma.model.WindSpeed
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
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
        return cityForecastsUiModels
    }

    fun todayUiModel(cityForecastUiModelList: List<CityForecastUiModel>): CityForecastUiModel? {
        return cityForecastUiModelList.find { DateUtils.isToday(it.date.timeInMillis) }
    }
}