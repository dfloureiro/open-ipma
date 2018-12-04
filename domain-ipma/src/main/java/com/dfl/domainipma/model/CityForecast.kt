package com.dfl.domainipma.model

data class CityForecast(
    val dataUpdate: String,
    val precipitationProbability: Double,
    val minimalTemperature: Double,
    val maximumTemperature: Double,
    val windDirection: WindDirection,
    val idWeatherType: Int,
    val classWindSpeed: Int,
    val forecastDate: String
)