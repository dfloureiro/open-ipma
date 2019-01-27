package com.dfl.dataipma.datasource

import com.dfl.dataipma.IpmaClient
import com.dfl.dataipma.model.WeatherTypesDto

class WeatherTypesDataSource(private val ipmaClient: IpmaClient) {

    suspend fun getWeatherTypes(): WeatherTypesDto {
        return ipmaClient.getWeatherTypesAsync().await()
    }
}