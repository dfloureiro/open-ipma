package com.dfl.openipma.home

data class HomeForecastUiModel(
    val cityId: Int,
    val cityName: String,
    val minTemperature: String,
    val maxTemperature: String,
    val precipitationProbability: String,
    val windSpeedDescription: String,
    val windRotation: Float,
    val weatherDescription: String,
    val weatherTypeResourceId: Int,
    val cardBackgroundColor: Int,
    val isClosestCity: Boolean
)