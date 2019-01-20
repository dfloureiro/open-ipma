package com.dfl.domainipma.usecase

import com.dfl.domainipma.model.City
import com.dfl.domainipma.repository.LocationRepository

class GetClosestCityUseCase(private val locationRepository: LocationRepository) {

    suspend fun buildUseCase(params: Params): City {
        return locationRepository.getClosestCity(params.cities, params.longitude, params.latitude)
    }

    data class Params(val cities: List<City>, val longitude: Double, val latitude: Double)
}