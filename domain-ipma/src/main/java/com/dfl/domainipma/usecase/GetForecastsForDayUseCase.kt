package com.dfl.domainipma.usecase

import com.dfl.domainipma.model.City
import com.dfl.domainipma.model.Day
import com.dfl.domainipma.model.Forecast
import com.dfl.domainipma.repository.IpmaRepository

class GetForecastsForDayUseCase(private val ipmaRepository: IpmaRepository) {

    suspend fun buildUseCase(params: Params): List<Forecast> {
        return moveClosestCityToFirstPosition(
            params.closestCity,
            ipmaRepository.getForecastsForDay(params.day.id)
        )
    }

    private fun moveClosestCityToFirstPosition(
        closestCity: City?,
        forecasts: List<Forecast>
    ): List<Forecast> {
        return forecasts.toMutableList()
            .let { mutableForecasts ->
                if (closestCity != null) {
                    forecasts.find { it.cityId == closestCity.id }
                        ?.also { forecast ->
                            mutableForecasts.remove(forecast)
                            mutableForecasts.add(0, forecast.copy(isClosestCity = true))
                        }
                }
                mutableForecasts
            }
    }

    data class Params(val day: Day, val closestCity: City?)
}
