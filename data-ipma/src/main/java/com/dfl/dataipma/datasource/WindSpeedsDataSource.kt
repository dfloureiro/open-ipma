package com.dfl.dataipma.datasource

import com.dfl.dataipma.IpmaClient
import com.dfl.dataipma.model.WindSpeedsDto

class WindSpeedsDataSource(private val ipmaClient: IpmaClient) {

    suspend fun getWindSpeeds(): WindSpeedsDto {
        return ipmaClient.getWindSpeedsAsync().await()
    }
}