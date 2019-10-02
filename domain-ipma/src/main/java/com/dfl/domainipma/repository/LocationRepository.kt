package com.dfl.domainipma.repository

import com.dfl.domainipma.model.City

interface LocationRepository {

    suspend fun getClosestCity(cities: List<City>, longitude: Double, latitude: Double): City
}
