package com.dfl.openipma.city

import java.util.*

data class CityForecastUiModel(
    val date: GregorianCalendar,
    val minTemperature: String,
    val maxTemperature: String,
    val precipitationProbability: String,
    val windSpeedDescription: String,
    val windRotation: Float,
    val windDirection: String,
    val weatherDescription: String,
    val weatherTypeResourceId: Int,
    val cardBackgroundColor: Int,
    val isToday: Boolean,
    val temperatureStatusStringId: Int
)