package com.dfl.domainipma.usecase

import com.dfl.domainipma.model.WindSpeed
import com.dfl.domainipma.repository.IpmaRepository

class GetWindSpeedsUseCase(private val ipmaRepository: IpmaRepository) {

    suspend fun buildUseCase(): List<WindSpeed> {
        return ipmaRepository.getWindSpeeds()
    }
}