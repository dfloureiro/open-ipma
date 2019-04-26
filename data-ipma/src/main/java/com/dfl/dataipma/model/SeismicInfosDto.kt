package com.dfl.dataipma.model

import com.squareup.moshi.Json

data class SeismicInfosDto(
    @Json(name = "country")
    val country: String,
    @Json(name = "data")
    val `data`: List<SeismicInfoDto>,
    @Json(name = "idArea")
    val idArea: Int,
    @Json(name = "lastSismicActivityDate")
    val lastSismicActivityDate: String,
    @Json(name = "owner")
    val owner: String,
    @Json(name = "updateDate")
    val updateDate: String
)

data class SeismicInfoDto(
    @Json(name = "dataUpdate")
    val dataUpdate: String,
    @Json(name = "degree")
    val degree: Any?,
    @Json(name = "depth")
    val depth: Int,
    @Json(name = "googlemapref")
    val googlemapref: String,
    @Json(name = "lat")
    val lat: String,
    @Json(name = "local")
    val local: Any?,
    @Json(name = "lon")
    val lon: String,
    @Json(name = "magType")
    val magType: String,
    @Json(name = "magnitud")
    val magnitud: String,
    @Json(name = "obsRegion")
    val obsRegion: String,
    @Json(name = "sensed")
    val sensed: Boolean?,
    @Json(name = "shakemapid")
    val shakemapid: String,
    @Json(name = "shakemapref")
    val shakemapref: String,
    @Json(name = "source")
    val source: String,
    @Json(name = "tensorRef")
    val tensorRef: String,
    @Json(name = "time")
    val time: String
)