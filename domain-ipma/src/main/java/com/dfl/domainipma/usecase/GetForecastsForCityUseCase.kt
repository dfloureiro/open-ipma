package com.dfl.domainipma.usecase

import com.dfl.domainipma.model.Forecast
import com.dfl.domainipma.repository.IpmaRepository

class GetForecastsForCityUseCase(private val ipmaRepository: IpmaRepository) {

    suspend fun buildUseCase(params: Params): List<Forecast> {
        return ipmaRepository.getForecasts(params.cityId)
    }

    data class Params(val cityId: Int)
}