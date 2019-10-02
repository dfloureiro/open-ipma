package com.dfl.domainipma.model

data class Forecast(
    val cityId: Int,
    val minTemp: String,
    val maxTemp: String,
    val precipitation: String,
    val windDirection: WindDirection,
    val windSpeed: Int,
    val weatherType: Int,
    val isClosestCity: Boolean = false
)
