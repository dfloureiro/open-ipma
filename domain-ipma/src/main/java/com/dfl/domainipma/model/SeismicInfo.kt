package com.dfl.domainipma.model

data class SeismicInfo(
    val latitude: Double,
    val longitude: Double,
    val depth: Int,
    val magnitude: String,
    val sensed: Boolean
)