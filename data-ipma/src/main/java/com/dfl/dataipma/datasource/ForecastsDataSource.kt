package com.dfl.dataipma.datasource

import com.dfl.dataipma.IpmaClient
import com.dfl.dataipma.model.ForecastsDto
import kotlinx.coroutines.Deferred

class ForecastsDataSource(private val ipmaClient: IpmaClient) {

    fun getForecasts(cityId: Int): Deferred<ForecastsDto> {
        return ipmaClient.getForecast(cityId)
    }
}