package com.dfl.dataipma.mapper

import com.dfl.dataipma.model.GlobalIdDto
import com.dfl.dataipma.model.GlobalIdsDto
import com.dfl.domainipma.model.City

class GlobalIdsDtoToCityListMapper {

    fun map(globalIdsDto: GlobalIdsDto): List<City> {
        return globalIdsDto.data.map { globalIdToCity(it) }
    }

    private fun globalIdToCity(globalIdDto: GlobalIdDto): City {
        return City(
            globalIdDto.globalIdLocal,
            globalIdDto.local,
            globalIdDto.latitude.toDouble(),
            globalIdDto.longitude.toDouble()
        )
    }
}
