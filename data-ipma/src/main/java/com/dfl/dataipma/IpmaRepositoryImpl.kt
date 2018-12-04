package com.dfl.dataipma

import com.dfl.dataipma.datasource.ForecastsDataSource
import com.dfl.dataipma.datasource.GlobalIdsDataSource
import com.dfl.dataipma.mapper.ForecastsCityDtoToForecastsListMapper
import com.dfl.dataipma.mapper.ForecastsDayDtoToForecastsListMapper
import com.dfl.dataipma.mapper.GlobalIdsDtoToCityListMapper
import com.dfl.domainipma.model.City
import com.dfl.domainipma.model.CityForecast
import com.dfl.domainipma.model.Forecast
import com.dfl.domainipma.repository.IpmaRepository

class IpmaRepositoryImpl(
    private val forecastsDataSource: ForecastsDataSource,
    private val forecastsCityDtoToForecastsListMapper: ForecastsCityDtoToForecastsListMapper,
    private val forecastsDayDtoToForecastsListMapper: ForecastsDayDtoToForecastsListMapper,
    private val globalIdsDataSource: GlobalIdsDataSource,
    private val globalIdsDtoToCityListMapper: GlobalIdsDtoToCityListMapper
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
}