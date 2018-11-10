package com.dfl.dataipma.datasource

import com.dfl.dataipma.IpmaClient
import com.dfl.dataipma.model.GlobalIdsDto
import kotlinx.coroutines.Deferred

class GlobalIdsDataSource(private val ipmaClient: IpmaClient) {

    fun getGlobalIds(): Deferred<GlobalIdsDto> {
        return ipmaClient.getLocalGlobalIds()
    }

}