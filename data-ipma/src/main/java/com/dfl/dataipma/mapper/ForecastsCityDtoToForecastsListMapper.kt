package com.dfl.dataipma.mapper

import com.dfl.dataipma.model.ForecastCityDto
import com.dfl.dataipma.model.ForecastsCityDto
import com.dfl.domainipma.model.CityForecast
import com.dfl.domainipma.model.WindDirection
import com.dfl.domainipma.repository.DateRepository

class ForecastsCityDtoToForecastsListMapper(private val dateRepository: DateRepository) {

    fun map(forecastsCityDto: ForecastsCityDto): List<CityForecast> {
        val dataUpdate = forecastsCityDto.dataUpdate
        return forecastsCityDto.data.map { forecastDtoToForecast(dataUpdate, it) }
    }

    private fun forecastDtoToForecast(dataUpdate: String, forecastCityDto: ForecastCityDto): CityForecast {
        return CityForecast(
            forecastCityDto.tMin,
            forecastCityDto.tMax,
            forecastCityDto.precipitaProb,
            WindDirection.valueOf(forecastCityDto.predWindDir),
            forecastCityDto.classWindSpeed,
            forecastCityDto.idWeatherType,
            forecastCityDto.forecastDate,
            dateRepository.isToday(forecastCityDto.forecastDate)
        )
    }
}