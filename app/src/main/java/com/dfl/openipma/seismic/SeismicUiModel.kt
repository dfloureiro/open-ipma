package com.dfl.openipma.seismic

data class SeismicUiModel(
    val magnitud: String,
    val region: String,
    val time: String,
    val latitude: Double,
    val longitude: Double,
    val sensed: Boolean
)