package com.dfl.openipma

data class ForecastUiModel(
    val cityId: Int,
    val cityName: String,
    val minTemperature: String,
    val maxTemperature: String,
    val precipitationProbability: String,
    val windRotation: Float
)