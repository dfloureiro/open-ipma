package com.dfl.domainipma.usecase

import com.dfl.domainipma.model.Day
import com.dfl.domainipma.model.Forecast
import com.dfl.domainipma.repository.IpmaRepository

class GetForecastsForDayUseCase(private val ipmaRepository: IpmaRepository) {

    suspend fun buildUseCase(params: Params): List<Forecast> {
        return ipmaRepository.getForecastsForDay(params.day.id)
    }

    data class Params(val day: Day)
}