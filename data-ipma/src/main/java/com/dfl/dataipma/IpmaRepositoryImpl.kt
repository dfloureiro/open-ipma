package com.dfl.dataipma

import com.dfl.dataipma.datasource.ForecastsDataSource
import com.dfl.dataipma.datasource.GlobalIdsDataSource
import com.dfl.dataipma.datasource.WeatherTypesDataSource
import com.dfl.dataipma.datasource.WindSpeedsDataSource
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
    private val windSpeedsDtoToWindSpeedsListMapper: WindSpeedsDtoToWindSpeedsListMapper
) : IpmaRepository {

    override suspend fun getForecastsForCity(cityId: Int): List<CityForecast> {
        return forecastsCityDtoToForecastsListMapper.map(forecastsDataSource.getForecastsForCity(cityId).await())
    }

    override suspend fun getForecastsForDay(dayId: Int): List<Forecast> {
        return forecastsDayDtoToForecastsListMapper.map(forecastsDataSource.getForecastsForDay(dayId).await())
    }

    override suspend fun getCities(): List<City> {
        return globalIdsDtoToCityListMapper.map(globalIdsDataSource.getGlobalIds().await())
    }

    override suspend fun getWeatherTypes(): List<WeatherType> {
        return weatherTypeDtoToWeatherTypeListMapper.map(weatherTypesDataSource.getWeatherTypes().await())
    }

    override suspend fun getWindSpeeds(): List<WindSpeed> {
        return windSpeedsDtoToWindSpeedsListMapper.map(windSpeedsDataSource.getWindSpeeds().await())
    }
}