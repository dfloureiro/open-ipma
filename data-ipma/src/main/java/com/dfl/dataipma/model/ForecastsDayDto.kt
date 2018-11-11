package com.dfl.dataipma.model

import com.squareup.moshi.Json


data class ForecastsDayDto(
    @Json(name = "country")
    val country: String,
    @Json(name = "data")
    val data: List<ForecastDayDto>,
    @Json(name = "dataUpdate")
    val dataUpdate: String,
    @Json(name = "forecastDate")
    val forecastDate: String,
    @Json(name = "owner")
    val owner: String
)

data class ForecastDayDto(
    @Json(name = "classPrecInt")
    val classPrecInt: Int,
    @Json(name = "classWindSpeed")
    val classWindSpeed: Int,
    @Json(name = "globalIdLocal")
    val globalIdLocal: Int,
    @Json(name = "idWeatherType")
    val idWeatherType: Int,
    @Json(name = "latitude")
    val latitude: String,
    @Json(name = "longitude")
    val longitude: String,
    @Json(name = "precipitaProb")
    val precipitaProb: String,
    @Json(name = "predWindDir")
    val predWindDir: String,
    @Json(name = "tMax")
    val tMax: String,
    @Json(name = "tMin")
    val tMin: String
)