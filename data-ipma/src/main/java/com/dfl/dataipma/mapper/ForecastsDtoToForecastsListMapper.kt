package com.dfl.dataipma.mapper

import com.dfl.dataipma.model.ForecastDto
import com.dfl.dataipma.model.ForecastsDto
import com.dfl.domainipma.model.Forecast

class ForecastsDtoToForecastsListMapper {

    fun map(forecastsDto: ForecastsDto): List<Forecast> {
        return forecastsDto.data.map { forecastDtoToForecast(it) }
    }

    private fun forecastDtoToForecast(forecastDto: ForecastDto): Forecast {
        return Forecast(forecastDto.forecastDate)
    }
}

