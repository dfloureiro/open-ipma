package com.dfl.domainipma.model

data class CityForecast(
    val minTemp: String,
    val maxTemp: String,
    val precipitation: String,
    val windDirection: WindDirection,
    val windSpeed: Int,
    val weatherType: Int,
    val forecastDate: String,
    val isToday: Boolean
)