package com.dfl.dataipma.model

import com.squareup.moshi.Json

data class WindSpeedsDto(
    @Json(name = "country")
    val country: String,
    @Json(name = "data")
    val data: List<WindSpeedDto>,
    @Json(name = "owner")
    val owner: String
)

data class WindSpeedDto(
    @Json(name = "classWindSpeed")
    val classWindSpeed: String,
    @Json(name = "descClassWindSpeedDailyEN")
    val descClassWindSpeedDailyEN: String,
    @Json(name = "descClassWindSpeedDailyPT")
    val descClassWindSpeedDailyPT: String
)