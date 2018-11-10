package com.dfl.dataipma.model

import com.squareup.moshi.Json


data class ForecastsDto(
    @Json(name = "country")
    val country: String,
    @Json(name = "data")
    val `data`: List<ForecastDto>,
    @Json(name = "dataUpdate")
    val dataUpdate: String,
    @Json(name = "globalIdLocal")
    val globalIdLocal: Int,
    @Json(name = "owner")
    val owner: String
)

data class ForecastDto(
    @Json(name = "classWindSpeed")
    val classWindSpeed: Int,
    @Json(name = "forecastDate")
    val forecastDate: String,
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