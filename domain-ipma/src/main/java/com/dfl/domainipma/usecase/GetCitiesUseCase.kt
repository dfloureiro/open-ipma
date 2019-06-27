package com.dfl.domainipma.usecase

import com.dfl.domainipma.model.City
import com.dfl.domainipma.repository.IpmaRepository

class GetCitiesUseCase(private val ipmaRepository: IpmaRepository) {

    suspend fun buildUseCase(): List<City> {
        return ipmaRepository.getCities()
    }
}
