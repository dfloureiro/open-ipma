package com.dfl.dataipma.datasource

import com.dfl.dataipma.IpmaClient
import com.dfl.dataipma.model.ForecastsCityDto
import com.dfl.dataipma.model.ForecastsDayDto

class ForecastsDataSource(private val ipmaClient: IpmaClient) {

    suspend fun getForecastsForCity(cityId: Int): ForecastsCityDto {
        return ipmaClient.getForecastsForCityAsync(cityId).await()
    }

    suspend fun getForecastsForDay(dayId: Int): ForecastsDayDto {
        return ipmaClient.getForecastsForDayAsync(dayId).await()
    }
}