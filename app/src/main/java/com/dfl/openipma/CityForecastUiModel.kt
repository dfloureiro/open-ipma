package com.dfl.openipma

import java.util.*

data class CityForecastUiModel(
    val cityName: String,
    val date: GregorianCalendar,
    val dateLastUpdate: GregorianCalendar,
    val weatherType: Int,
    val minimalTemperature: Int,
    val maximumTemperature: Int,
    val windClass: Int,
    val precipitationProbability: Int
)