package com.dfl.dataipma.mapper

import com.dfl.dataipma.model.ForecastCityDto
import com.dfl.dataipma.model.ForecastsCityDto
import com.dfl.domainipma.model.CityForecast
import com.dfl.domainipma.model.TemperatureStatus
import com.dfl.domainipma.model.WindDirection
import com.dfl.domainipma.repository.DateRepository
import kotlin.math.roundToInt

class ForecastsCityDtoToForecastsListMapper(private val dateRepository: DateRepository) {

    fun map(forecastsCityDto: ForecastsCityDto): List<CityForecast> {
        val dataUpdate = forecastsCityDto.dataUpdate
        val forecastsSorted = forecastsCityDto.data.sortedBy { dateRepository.getTimeInMillis(it.forecastDate) }
        return forecastsCityDto.data.map { forecastDtoToForecast(dataUpdate, it, forecastsSorted) }
    }

    private fun forecastDtoToForecast(
        dataUpdate: String,
        forecastCityDto: ForecastCityDto,
        forecastsSorted: List<ForecastCityDto>
    ): CityForecast {
        val yesterdayForecast = getYesterdaysForecast(forecastCityDto, forecastsSorted)
        val temperatureStatus = getTemperatureStatus(
            yesterdayForecast.tMin.toDouble(),
            yesterdayForecast.tMax.toDouble(),
            forecastCityDto.tMin.toDouble(),
            forecastCityDto.tMax.toDouble()
        )
        return CityForecast(
            forecastCityDto.tMin.toDouble().roundToInt().toString(),
            forecastCityDto.tMax.toDouble().roundToInt().toString(),
            forecastCityDto.precipitaProb,
            WindDirection.valueOf(forecastCityDto.predWindDir),
            forecastCityDto.classWindSpeed,
            forecastCityDto.idWeatherType,
            forecastCityDto.forecastDate,
            dateRepository.isToday(forecastCityDto.forecastDate),
            temperatureStatus
        )
    }

    private fun getYesterdaysForecast(
        forecast: ForecastCityDto,
        forecastsSorted: List<ForecastCityDto>
    ): ForecastCityDto {
        val index = forecastsSorted.indexOf(forecast)
        val yesterdayForecastIndex = when {
            index <= 0 -> 0
            else -> index - 1
        }
        return forecastsSorted[yesterdayForecastIndex]
    }

    private fun getTemperatureStatus(
        yesterdayMinTemp: Double,
        yesterdayMaxTemp: Double,
        todayMinTemp: Double,
        todayMaxTemp: Double
    ): TemperatureStatus {
        // TODO move this to other place
        val averageYesterdayTemp = (yesterdayMaxTemp + yesterdayMinTemp) / 2
        val averageTodayTemp = (todayMaxTemp + todayMinTemp) / 2
        val difference = averageTodayTemp.compareTo(averageYesterdayTemp)
        return when {
            difference > 0 -> TemperatureStatus.INCREASE
            difference == 0 -> TemperatureStatus.EQUAL
            else -> TemperatureStatus.DECREASE
        }
    }
}