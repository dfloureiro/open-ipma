package com.dfl.openipma.seismic

interface MapFragment {

    fun moveMapCamera(latitude: Double, longitude: Double, zoom: Float = 15f)
}
