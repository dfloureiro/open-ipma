package com.dfl.dataipma.datasource

import com.dfl.dataipma.IpmaClient
import com.dfl.dataipma.model.GlobalIdsDto

class GlobalIdsDataSource(private val ipmaClient: IpmaClient) {

    suspend fun getGlobalIds(): GlobalIdsDto {
        return ipmaClient.getLocalGlobalIdsAsync().await()
    }

}