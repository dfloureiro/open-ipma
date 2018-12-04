package com.dfl.openipma

import com.dfl.domainipma.model.WindDirection
import java.util.*

data class CityForecastUiModel(
    val date: GregorianCalendar,
    val weatherType: Int,
    val minimalTemperature: Double,
    val maximumTemperature: Double,
    val windDirection: WindDirection,
    val idWeatherType: Int,
    val windSpeedClass: Int,
    val precipitationProbability: Double
)