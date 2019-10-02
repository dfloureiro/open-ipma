package com.dfl.dataipma.mapper

import com.dfl.common.dateFormatLanguageCode
import com.dfl.dataipma.model.WeatherTypeDto
import com.dfl.dataipma.model.WeatherTypesDto
import com.dfl.domainipma.model.WeatherType
import java.util.Locale

class WeatherTypeDtoToWeatherTypeListMapper {

    fun map(weatherTypesDto: WeatherTypesDto): List<WeatherType> {
        return weatherTypesDto.data.map { weatherTypeDtoToWeatherType(it) }
    }

    private fun weatherTypeDtoToWeatherType(weatherTypeDto: WeatherTypeDto): WeatherType {
        val weatherDescription =
            when (Locale.getDefault().language) {
                dateFormatLanguageCode -> weatherTypeDto.descIdWeatherTypePT
                else -> weatherTypeDto.descIdWeatherTypeEN
            }
        return WeatherType(
            weatherTypeDto.idWeatherType,
            weatherDescription
        )
    }
}
