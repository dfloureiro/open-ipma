package com.dfl.openipma.city

import com.dfl.common.dateFormatLanguageCode
import com.dfl.common.precipitationDivider
import com.dfl.domainipma.model.CityForecast
import com.dfl.domainipma.model.WeatherType
import com.dfl.domainipma.model.WindSpeed
import com.dfl.openipma.base.BaseUiModelMapper
import dagger.Reusable
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.Locale
import javax.inject.Inject

@Reusable
class CityForecastUiModelMapper @Inject constructor() : BaseUiModelMapper() {

    private val simpleDateFormat = SimpleDateFormat(
        com.dfl.common.dateFormat, Locale.forLanguageTag(
            dateFormatLanguageCode
        )
    )

    fun map(
        forecasts: List<CityForecast>,
        windSpeeds: List<WindSpeed>,
        weathersType: List<WeatherType>
    ): List<CityForecastUiModel> {
        return forecasts.map { forecast ->
            val weatherDescription =
                weathersType.find { it.weatherTypeId == forecast.weatherType }?.weatherTypeDescription
                    ?: defaultUnknownDescription
            val windSpeedDescription =
                windSpeeds.find { it.windSpeedId == forecast.windSpeed }?.windSpeedDescription
                    ?: defaultUnknownDescription
            CityForecastUiModel(
                getDayOfWeek(forecast.forecastDate),
                setTemperatureSuffix(forecast.minTemp),
                setTemperatureSuffix(forecast.maxTemp),
                setPrecipitationSuffix(forecast.precipitation.substringBefore(precipitationDivider)),
                windSpeedDescription,
                forecast.windDirection.rotation,
                forecast.windDirection.name,
                weatherDescription,
                getIcon(forecast.weatherType),
                getBackgroundColor(forecast.weatherType),
                forecast.isToday,
                getTemperatureStatusDescription(forecast.temperatureStatus)
            )
        }
    }

    private fun getDayOfWeek(date: String): String {
        val forecastDate = GregorianCalendar()
        simpleDateFormat.parse(date)?.also { forecastDate.time = it }
        return forecastDate.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()).orEmpty()
    }
}
