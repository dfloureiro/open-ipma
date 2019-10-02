package com.dfl.dataipma.mapper

import com.dfl.common.dateFormatLanguageCode
import com.dfl.dataipma.model.WindSpeedDto
import com.dfl.dataipma.model.WindSpeedsDto
import com.dfl.domainipma.model.WindSpeed
import java.util.Locale

class WindSpeedsDtoToWindSpeedsListMapper {

    fun map(windSpeedsDto: WindSpeedsDto): List<WindSpeed> {
        return windSpeedsDto.data.map { windSpeedDtoToWindSpeed(it) }
    }

    private fun windSpeedDtoToWindSpeed(windSpeedDto: WindSpeedDto): WindSpeed {
        val windSpeedDescription =
            when (Locale.getDefault().language) {
                dateFormatLanguageCode -> windSpeedDto.descClassWindSpeedDailyPT
                else -> windSpeedDto.descClassWindSpeedDailyEN
            }

        return WindSpeed(
            windSpeedDto.classWindSpeed.toInt(),
            windSpeedDescription
        )
    }
}
