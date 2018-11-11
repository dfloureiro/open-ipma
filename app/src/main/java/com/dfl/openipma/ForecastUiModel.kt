package com.dfl.openipma

data class ForecastUiModel(
    val cityName: String,
    val minTemperature: String,
    val maxTemperature: String,
    val precipitationProbability: String
)