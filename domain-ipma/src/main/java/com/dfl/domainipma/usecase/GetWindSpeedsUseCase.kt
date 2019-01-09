package com.dfl.domainipma.usecase

import com.dfl.domainipma.model.WindSpeed
import com.dfl.domainipma.repository.IpmaRepository

class GetWindSpeedsUseCase(private val ipmaRepository: IpmaRepository) {

    private var windSpeeds: List<WindSpeed> = listOf()

    suspend fun buildUseCase(): List<WindSpeed> {
        if (windSpeeds.isEmpty()) {
            windSpeeds = ipmaRepository.getWindSpeeds()
        }
        return windSpeeds
    }
}