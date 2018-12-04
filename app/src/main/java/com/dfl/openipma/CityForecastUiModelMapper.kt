package com.dfl.openipma

import com.dfl.domainipma.model.CityForecast
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CityForecastUiModelMapper @Inject constructor() {

    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.forLanguageTag("pt"))

    fun map(forecasts: List<CityForecast>): List<CityForecastUiModel> {
        val cityForecastsUiModels = mutableListOf<CityForecastUiModel>()
        forecasts.forEach {
            val forecastDate = GregorianCalendar()
            forecastDate.time = simpleDateFormat.parse(it.forecastDate)
            cityForecastsUiModels.add(
                CityForecastUiModel(
                    forecastDate,
                    it.idWeatherType,
                    it.minimalTemperature,
                    it.maximumTemperature,
                    it.windDirection,
                    it.idWeatherType,
                    it.classWindSpeed,
                    it.precipitationProbability
                )
            )
        }
        return cityForecastsUiModels
    }
}