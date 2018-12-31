package com.dfl.dataipma.datasource

import com.dfl.dataipma.IpmaClient
import com.dfl.dataipma.model.WindSpeedsDto
import kotlinx.coroutines.Deferred

class WindSpeedsDataSource(private val ipmaClient: IpmaClient) {

    fun getWindSpeeds(): Deferred<WindSpeedsDto> {
        return ipmaClient.getWindSpeeds()
    }
}