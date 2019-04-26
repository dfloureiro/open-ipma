package com.dfl.dataipma

import com.dfl.dataipma.datasource.*
import com.dfl.dataipma.mapper.*
import com.dfl.domainipma.model.*
import com.dfl.domainipma.repository.IpmaRepository

class IpmaRepositoryImpl(
    private val forecastsDataSource: ForecastsDataSource,
    private val forecastsCityDtoToForecastsListMapper: ForecastsCityDtoToForecastsListMapper,
    private val forecastsDayDtoToForecastsListMapper: ForecastsDayDtoToForecastsListMapper,
    private val globalIdsDataSource: GlobalIdsDataSource,
    private val globalIdsDtoToCityListMapper: GlobalIdsDtoToCityListMapper,
    private val weatherTypesDataSource: WeatherTypesDataSource,
    private val weatherTypeDtoToWeatherTypeListMapper: WeatherTypeDtoToWeatherTypeListMapper,
    private val windSpeedsDataSource: WindSpeedsDataSource,
    private val windSpeedsDtoToWindSpeedsListMapper: WindSpeedsDtoToWindSpeedsListMapper,
    private val seismicDataSource: SeismicDataSource,
    private val seismicInfosDtoToSeismicInfoListMapper: SeismicInfosDtoToSeismicInfoListMapper
) : IpmaRepository {

    override suspend fun getForecastsForCity(cityId: Int): List<CityForecast> {
        return forecastsCityDtoToForecastsListMapper.map(forecastsDataSource.getForecastsForCity(cityId))
    }

    override suspend fun getForecastsForDay(dayId: Int): List<Forecast> {
        return forecastsDayDtoToForecastsListMapper.map(forecastsDataSource.getForecastsForDay(dayId))
    }

    override suspend fun getCities(): List<City> {
        return globalIdsDtoToCityListMapper.map(globalIdsDataSource.getGlobalIds())
    }

    override suspend fun getWeatherTypes(): List<WeatherType> {
        return weatherTypeDtoToWeatherTypeListMapper.map(weatherTypesDataSource.getWeatherTypes())
    }

    override suspend fun getWindSpeeds(): List<WindSpeed> {
        return windSpeedsDtoToWindSpeedsListMapper.map(windSpeedsDataSource.getWindSpeeds())
    }

    override suspend fun getSeismicInfo(areaId: Int): List<SeismicInfo> {
        return seismicInfosDtoToSeismicInfoListMapper.map(seismicDataSource.getSeismicInfo(areaId))
    }
}