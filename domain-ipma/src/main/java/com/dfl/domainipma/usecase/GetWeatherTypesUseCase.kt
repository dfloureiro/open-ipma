package com.dfl.domainipma.usecase

import com.dfl.domainipma.model.WeatherType
import com.dfl.domainipma.repository.IpmaRepository

class GetWeatherTypesUseCase(private val ipmaRepository: IpmaRepository) {

    suspend fun buildUseCase(): List<WeatherType> {
        return ipmaRepository.getWeatherTypes()
    }
}