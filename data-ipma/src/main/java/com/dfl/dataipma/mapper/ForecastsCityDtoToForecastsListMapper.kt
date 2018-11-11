package com.dfl.dataipma.mapper

import com.dfl.dataipma.model.ForecastCityDto
import com.dfl.dataipma.model.ForecastsCityDto
import com.dfl.domainipma.model.Forecast

class ForecastsCityDtoToForecastsListMapper {

    fun map(forecastsCityDto: ForecastsCityDto): List<Forecast> {
        return forecastsCityDto.data.map { forecastDtoToForecast(it) }
    }

    private fun forecastDtoToForecast(forecastCityDto: ForecastCityDto): Forecast {
        return Forecast(forecastCityDto.forecastDate)
    }
}

