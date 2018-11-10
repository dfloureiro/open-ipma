package com.dfl.dataipma.model
import com.squareup.moshi.Json


data class GlobalIdsDto(
    @Json(name = "country")
    val country: String,
    @Json(name = "data")
    val `data`: List<GlobalIdDto>,
    @Json(name = "owner")
    val owner: String
)

data class GlobalIdDto(
    @Json(name = "globalIdLocal")
    val globalIdLocal: Int,
    @Json(name = "idAreaAviso")
    val idAreaAviso: String,
    @Json(name = "idConcelho")
    val idConcelho: Int,
    @Json(name = "idDistrito")
    val idDistrito: Int,
    @Json(name = "idRegiao")
    val idRegiao: Int,
    @Json(name = "latitude")
    val latitude: String,
    @Json(name = "local")
    val local: String,
    @Json(name = "longitude")
    val longitude: String
)