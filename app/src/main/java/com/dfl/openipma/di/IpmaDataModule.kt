package com.dfl.openipma.di

import com.dfl.dataipma.IpmaClient
import com.dfl.dataipma.IpmaRepositoryImpl
import com.dfl.dataipma.LocationRepositoryImpl
import com.dfl.dataipma.datasource.ForecastsDataSource
import com.dfl.dataipma.datasource.GlobalIdsDataSource
import com.dfl.dataipma.datasource.WeatherTypesDataSource
import com.dfl.dataipma.datasource.WindSpeedsDataSource
import com.dfl.dataipma.mapper.*
import com.dfl.domainipma.repository.IpmaRepository
import com.dfl.domainipma.repository.LocationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class IpmaDataModule {

    @Singleton
    @Provides
    fun locationRepository(): LocationRepository {
        return LocationRepositoryImpl()
    }

    @Singleton
    @Provides
    fun ipmaRepository(
        forecastsDataSource: ForecastsDataSource,
        forecastsCityDtoToForecastsListMapper: ForecastsCityDtoToForecastsListMapper,
        forecastsDayDtoToForecastsListMapper: ForecastsDayDtoToForecastsListMapper,
        globalIdsDataSource: GlobalIdsDataSource,
        globalIdsDtoToCityListMapper: GlobalIdsDtoToCityListMapper,
        weatherTypesDataSource: WeatherTypesDataSource,
        weatherTypeDtoToWeatherTypeListMapper: WeatherTypeDtoToWeatherTypeListMapper,
        windSpeedsDataSource: WindSpeedsDataSource,
        windSpeedsDtoToWindSpeedsListMapper: WindSpeedsDtoToWindSpeedsListMapper
    ): IpmaRepository {
        return IpmaRepositoryImpl(
            forecastsDataSource,
            forecastsCityDtoToForecastsListMapper,
            forecastsDayDtoToForecastsListMapper,
            globalIdsDataSource,
            globalIdsDtoToCityListMapper,
            weatherTypesDataSource,
            weatherTypeDtoToWeatherTypeListMapper,
            windSpeedsDataSource,
            windSpeedsDtoToWindSpeedsListMapper
        )
    }

    @Singleton
    @Provides
    fun forecastDataSource(ipmaClient: IpmaClient): ForecastsDataSource {
        return ForecastsDataSource(ipmaClient)
    }

    @Singleton
    @Provides
    fun globalIdsDataSource(ipmaClient: IpmaClient): GlobalIdsDataSource {
        return GlobalIdsDataSource(ipmaClient)
    }


    @Singleton
    @Provides
    fun weatherTypesDataSource(ipmaClient: IpmaClient): WeatherTypesDataSource {
        return WeatherTypesDataSource(ipmaClient)
    }

    @Singleton
    @Provides
    fun windSpeedsDataSource(ipmaClient: IpmaClient): WindSpeedsDataSource {
        return WindSpeedsDataSource(ipmaClient)
    }

    @Singleton
    @Provides
    fun forecastsCityDtoToForecastsListMapper(): ForecastsCityDtoToForecastsListMapper {
        return ForecastsCityDtoToForecastsListMapper()
    }

    @Singleton
    @Provides
    fun forecastsDayDtoToForecastsListMapper(): ForecastsDayDtoToForecastsListMapper {
        return ForecastsDayDtoToForecastsListMapper()
    }

    @Singleton
    @Provides
    fun weatherTypeDtoToWeatherTypeListMapper(): WeatherTypeDtoToWeatherTypeListMapper {
        return WeatherTypeDtoToWeatherTypeListMapper()
    }

    @Singleton
    @Provides
    fun windSpeedsDtoToWindSpeedsListMapper(): WindSpeedsDtoToWindSpeedsListMapper {
        return WindSpeedsDtoToWindSpeedsListMapper()
    }

    @Singleton
    @Provides
    fun globalIdsDtoToCityListMapper(): GlobalIdsDtoToCityListMapper {
        return GlobalIdsDtoToCityListMapper()
    }
}