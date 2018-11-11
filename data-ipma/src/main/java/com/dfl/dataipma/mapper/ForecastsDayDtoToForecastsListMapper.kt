package com.dfl.dataipma.mapper

import com.dfl.dataipma.model.ForecastDayDto
import com.dfl.dataipma.model.ForecastsDayDto
import com.dfl.domainipma.model.Forecast

class ForecastsDayDtoToForecastsListMapper {

    fun map(forecastsDayDto: ForecastsDayDto): List<Forecast> {
        return forecastsDayDto.data.map { forecastDtoToForecast(it) }
    }

    private fun forecastDtoToForecast(forecastDayDto: ForecastDayDto): Forecast {
        return Forecast(
            forecastDayDto.globalIdLocal,
            forecastDayDto.tMin,
            forecastDayDto.tMax,
            forecastDayDto.precipitaProb
        )
    }
}
