package com.dfl.domainipma.usecase

import com.dfl.domainipma.model.WeatherType
import com.dfl.domainipma.repository.IpmaRepository

class GetWeatherTypesUseCase(private val ipmaRepository: IpmaRepository) {

    private var weatherTypes: List<WeatherType> = listOf()

    suspend fun buildUseCase(): List<WeatherType> {
        if (weatherTypes.isEmpty()) {
            weatherTypes = ipmaRepository.getWeatherTypes()
        }
        return weatherTypes
    }
}