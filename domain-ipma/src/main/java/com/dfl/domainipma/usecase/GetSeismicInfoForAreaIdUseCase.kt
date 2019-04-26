package com.dfl.domainipma.usecase

import com.dfl.domainipma.model.SeismicInfo
import com.dfl.domainipma.repository.IpmaRepository

class GetSeismicInfoForAreaIdUseCase(private val ipmaRepository: IpmaRepository) {

    suspend fun buildUseCase(params: Params): List<SeismicInfo> {
        return ipmaRepository.getSeismicInfo(params.areaId)
    }

    data class Params(val areaId: Int)
}