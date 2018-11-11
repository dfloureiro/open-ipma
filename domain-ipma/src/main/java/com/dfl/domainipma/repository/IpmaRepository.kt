package com.dfl.domainipma.repository

import com.dfl.domainipma.model.City
import com.dfl.domainipma.model.Forecast

interface IpmaRepository {

    suspend fun getForecastsForCity(cityId: Int): List<Forecast>

    suspend fun getForecastsForDay(dayId: Int): List<Forecast>

    suspend fun getCities(): List<City>
}