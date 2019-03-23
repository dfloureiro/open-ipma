package com.dfl.openipma.service

data class NotificationContent(
    val cityId : Int,
    val maximumTemperature: String,
    val minimumTemperature: String,
    val cityName: String,
    val text: String,
    val iconResourceId: Int
)