package com.dfl.domainipma.repository

import com.dfl.domainipma.model.*

interface IpmaRepository {

    suspend fun getForecastsForCity(cityId: Int): List<CityForecast>

    suspend fun getForecastsForDay(dayId: Int): List<Forecast>

    suspend fun getCities(): List<City>

    suspend fun getWeatherTypes(): List<WeatherType>

    suspend fun getWindSpeeds(): List<WindSpeed>

    suspend fun getSeismicInfo(areaId: Int): List<SeismicInfo>
}