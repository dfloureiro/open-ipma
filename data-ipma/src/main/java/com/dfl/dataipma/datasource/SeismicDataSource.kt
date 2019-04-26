package com.dfl.dataipma.datasource

import com.dfl.dataipma.IpmaClient
import com.dfl.dataipma.model.SeismicInfosDto

class SeismicDataSource(private val ipmaClient: IpmaClient) {

    suspend fun getSeismicInfo(areaId: Int): SeismicInfosDto {
        return ipmaClient.getSeismicForAreaAsync(areaId).await()
    }
}