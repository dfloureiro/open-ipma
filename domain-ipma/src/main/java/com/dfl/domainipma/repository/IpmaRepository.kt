package com.dfl.domainipma.repository

import com.dfl.domainipma.model.City
import com.dfl.domainipma.model.Forecast

interface IpmaRepository {

    suspend fun getForecasts(cityId: Int): List<Forecast>

    suspend fun getCities(): List<City>
}