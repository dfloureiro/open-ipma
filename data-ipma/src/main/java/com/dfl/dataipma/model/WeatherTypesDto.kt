package com.dfl.dataipma.model

import com.squareup.moshi.Json

data class WeatherTypesDto(
    @Json(name = "country")
    val country: String,
    @Json(name = "data")
    val data: List<WeatherTypeDto>,
    @Json(name = "owner")
    val owner: String
)

data class WeatherTypeDto(
    @Json(name = "descIdWeatherTypeEN")
    val descIdWeatherTypeEN: String,
    @Json(name = "descIdWeatherTypePT")
    val descIdWeatherTypePT: String,
    @Json(name = "idWeatherType")
    val idWeatherType: Int
)
