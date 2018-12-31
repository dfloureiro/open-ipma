package com.dfl.dataipma.datasource

import com.dfl.dataipma.IpmaClient
import com.dfl.dataipma.model.WeatherTypesDto
import kotlinx.coroutines.Deferred

class WeatherTypesDataSource(private val ipmaClient: IpmaClient) {

    fun getWeatherTypes(): Deferred<WeatherTypesDto> {
        return ipmaClient.getWeatherTypes()
    }
}