package com.dfl.domainipma.usecase

import com.dfl.domainipma.model.CityForecast
import com.dfl.domainipma.repository.IpmaRepository

class GetForecastsForCityUseCase(private val ipmaRepository: IpmaRepository) {

    suspend fun buildUseCase(params: Params): List<CityForecast> {
        return ipmaRepository.getForecastsForCity(params.cityId)
    }

    data class Params(val cityId: Int)
}