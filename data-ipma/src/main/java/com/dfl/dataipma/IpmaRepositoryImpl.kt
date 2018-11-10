package com.dfl.dataipma

import com.dfl.dataipma.datasource.ForecastsDataSource
import com.dfl.dataipma.datasource.GlobalIdsDataSource
import com.dfl.dataipma.mapper.ForecastsDtoToForecastsListMapper
import com.dfl.dataipma.mapper.GlobalIdsDtoToCityListMapper
import com.dfl.domainipma.model.City
import com.dfl.domainipma.model.Forecast
import com.dfl.domainipma.repository.IpmaRepository

class IpmaRepositoryImpl(
    private val forecastsDataSource: ForecastsDataSource,
    private val forecastsDtoToForecastsListMapper: ForecastsDtoToForecastsListMapper,
    private val globalIdsDataSource: GlobalIdsDataSource,
    private val globalIdsDtoToCityListMapper: GlobalIdsDtoToCityListMapper
) : IpmaRepository {

    override suspend fun getForecasts(cityId: Int): List<Forecast> {
        return forecastsDtoToForecastsListMapper.map(forecastsDataSource.getForecasts(cityId).await())
    }

    override suspend fun getCities(): List<City> {
        return globalIdsDtoToCityListMapper.map(globalIdsDataSource.getGlobalIds().await())
    }
}