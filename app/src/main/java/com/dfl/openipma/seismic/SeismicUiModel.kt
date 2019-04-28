package com.dfl.openipma.seismic

data class SeismicUiModel(
    val latitude: Double,
    val longitude: Double,
    val depth: Int,
    val magnitude: String,
    val sensed: Boolean
)