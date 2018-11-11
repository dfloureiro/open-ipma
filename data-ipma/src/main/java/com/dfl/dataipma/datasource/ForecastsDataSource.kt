package com.dfl.dataipma.datasource

import com.dfl.dataipma.IpmaClient
import com.dfl.dataipma.model.ForecastsCityDto
import com.dfl.dataipma.model.ForecastsDayDto
import kotlinx.coroutines.Deferred

class ForecastsDataSource(private val ipmaClient: IpmaClient) {

    fun getForecastsForCity(cityId: Int): Deferred<ForecastsCityDto> {
        return ipmaClient.getForecastsForCity(cityId)
    }

    fun getForecastsForDay(dayId: Int): Deferred<ForecastsDayDto> {
        return ipmaClient.getForecastsForDay(dayId)
    }
}