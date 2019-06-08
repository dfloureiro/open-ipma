package com.dfl.openipma.di.modules

import com.dfl.dataipma.DateRepositoryImpl
import com.dfl.dataipma.IpmaClient
import com.dfl.dataipma.IpmaRepositoryImpl
import com.dfl.dataipma.LocationRepositoryImpl
import com.dfl.dataipma.mapper.*
import com.dfl.domainipma.repository.DateRepository
import com.dfl.domainipma.repository.IpmaRepository
import com.dfl.domainipma.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module(includes = [NetworkModule::class])
object IpmaDataModule {

    @Reusable
    @Provides
    @JvmStatic
    fun locationRepository(): LocationRepository {
        return LocationRepositoryImpl()
    }

    @Reusable
    @Provides
    @JvmStatic
    fun ipmaRepository(
        ipmaClient: IpmaClient,
        forecastsCityDtoToForecastsListMapper: ForecastsCityDtoToForecastsListMapper,
        forecastsDayDtoToForecastsListMapper: ForecastsDayDtoToForecastsListMapper,
        globalIdsDtoToCityListMapper: GlobalIdsDtoToCityListMapper,
        weatherTypeDtoToWeatherTypeListMapper: WeatherTypeDtoToWeatherTypeListMapper,
        windSpeedsDtoToWindSpeedsListMapper: WindSpeedsDtoToWindSpeedsListMapper,
        seismicInfosDtoToSeismicInfoListMapper: SeismicInfosDtoToSeismicInfoListMapper
    ): IpmaRepository {
        return IpmaRepositoryImpl(
            ipmaClient,
            forecastsCityDtoToForecastsListMapper,
            forecastsDayDtoToForecastsListMapper,
            globalIdsDtoToCityListMapper,
            weatherTypeDtoToWeatherTypeListMapper,
            windSpeedsDtoToWindSpeedsListMapper,
            seismicInfosDtoToSeismicInfoListMapper
        )
    }

    @Reusable
    @Provides
    @JvmStatic
    fun dateRepository(): DateRepository {
        return DateRepositoryImpl()
    }

    @Reusable
    @Provides
    @JvmStatic
    fun forecastsCityDtoToForecastsListMapper(dateRepository: DateRepository): ForecastsCityDtoToForecastsListMapper {
        return ForecastsCityDtoToForecastsListMapper(dateRepository)
    }

    @Reusable
    @Provides
    @JvmStatic
    fun forecastsDayDtoToForecastsListMapper(): ForecastsDayDtoToForecastsListMapper {
        return ForecastsDayDtoToForecastsListMapper()
    }

    @Reusable
    @Provides
    @JvmStatic
    fun weatherTypeDtoToWeatherTypeListMapper(): WeatherTypeDtoToWeatherTypeListMapper {
        return WeatherTypeDtoToWeatherTypeListMapper()
    }

    @Reusable
    @Provides
    @JvmStatic
    fun windSpeedsDtoToWindSpeedsListMapper(): WindSpeedsDtoToWindSpeedsListMapper {
        return WindSpeedsDtoToWindSpeedsListMapper()
    }

    @Reusable
    @Provides
    @JvmStatic
    fun globalIdsDtoToCityListMapper(): GlobalIdsDtoToCityListMapper {
        return GlobalIdsDtoToCityListMapper()
    }

    @Reusable
    @Provides
    @JvmStatic
    fun seismicInfosDtoToSeismicInfoListMapper(): SeismicInfosDtoToSeismicInfoListMapper {
        return SeismicInfosDtoToSeismicInfoListMapper()
    }
}