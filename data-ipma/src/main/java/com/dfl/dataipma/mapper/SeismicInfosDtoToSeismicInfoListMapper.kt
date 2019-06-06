package com.dfl.dataipma.mapper

import com.dfl.dataipma.model.SeismicInfoDto
import com.dfl.dataipma.model.SeismicInfosDto
import com.dfl.domainipma.model.SeismicInfo

class SeismicInfosDtoToSeismicInfoListMapper {

    fun map(seismicInfosDto: SeismicInfosDto): List<SeismicInfo> {
        return seismicInfosDto.data.map { seismicInfoDtoToSeismicInfo(it) }
    }

    private fun seismicInfoDtoToSeismicInfo(seismicInfoDto: SeismicInfoDto): SeismicInfo {
        return SeismicInfo(
            seismicInfoDto.magnitud,
            seismicInfoDto.obsRegion,
            seismicInfoDto.time,
            seismicInfoDto.lat.toDouble(),
            seismicInfoDto.lon.toDouble(),
            seismicInfoDto.sensed ?: false
        )
    }
}