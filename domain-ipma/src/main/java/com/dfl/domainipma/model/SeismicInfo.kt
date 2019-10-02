package com.dfl.domainipma.model

data class SeismicInfo(
    val magnitude: String,
    val region: String,
    val time: String,
    val latitude: Double,
    val longitude: Double,
    val sensed: Boolean
)
