package com.dfl.dataipma

import com.dfl.dataipma.mapper.*
import com.dfl.domainipma.model.*
import com.dfl.domainipma.repository.IpmaRepository

class IpmaRepositoryImpl(
    private val ipmaClient: IpmaClient,
    private val forecastsCityDtoToForecastsListMapper: ForecastsCityDtoToForecastsListMapper,
    private val forecastsDayDtoToForecastsListMapper: ForecastsDayDtoToForecastsListMapper,
    private val globalIdsDtoToCityListMapper: GlobalIdsDtoToCityListMapper,
    private val weatherTypeDtoToWeatherTypeListMapper: WeatherTypeDtoToWeatherTypeListMapper,
    private val windSpeedsDtoToWindSpeedsListMapper: WindSpeedsDtoToWindSpeedsListMapper,
    private val seismicInfosDtoToSeismicInfoListMapper: SeismicInfosDtoToSeismicInfoListMapper
) : IpmaRepository {

    override suspend fun getForecastsForCity(cityId: Int): List<CityForecast> {
        return forecastsCityDtoToForecastsListMapper.map(ipmaClient.getForecastsForCity(cityId))
    }

    override suspend fun getForecastsForDay(dayId: Int): List<Forecast> {
        return forecastsDayDtoToForecastsListMapper.map(ipmaClient.getForecastsForDay(dayId))
    }

    override suspend fun getCities(): List<City> {
        return globalIdsDtoToCityListMapper.map(ipmaClient.getLocalGlobalIds())
    }

    override suspend fun getWeatherTypes(): List<WeatherType> {
        return weatherTypeDtoToWeatherTypeListMapper.map(ipmaClient.getWeatherTypes())
    }

    override suspend fun getWindSpeeds(): List<WindSpeed> {
        return windSpeedsDtoToWindSpeedsListMapper.map(ipmaClient.getWindSpeeds())
    }

    override suspend fun getSeismicInfo(areaId: Int): List<SeismicInfo> {
        return seismicInfosDtoToSeismicInfoListMapper.map(ipmaClient.getSeismicForArea(areaId))
    }
}