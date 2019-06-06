package com.dfl.openipma.city

data class CityForecastUiModel(
    val dayOfWeek: String,
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