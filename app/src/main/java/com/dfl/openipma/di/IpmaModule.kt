package com.dfl.openipma.di

import com.dfl.dataipma.IpmaClient
import com.dfl.dataipma.IpmaRepositoryImpl
import com.dfl.dataipma.datasource.ForecastsDataSource
import com.dfl.dataipma.datasource.GlobalIdsDataSource
import com.dfl.dataipma.datasource.WeatherTypesDataSource
import com.dfl.dataipma.datasource.WindSpeedsDataSource
import com.dfl.dataipma.mapper.*
import com.dfl.domainipma.repository.IpmaRepository
import com.dfl.domainipma.usecase.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class IpmaModule {

    companion object {
        const val baseUrl = "http://api.ipma.pt/"
    }

    @Singleton
    @Provides
    fun ipmaClient(
        coroutineCallAdapterFactory: CoroutineCallAdapterFactory,
        moshiConverterFactory: MoshiConverterFactory,
        okHttpClient: OkHttpClient
    ): IpmaClient {
        return Retrofit.Builder()
            .addCallAdapterFactory(coroutineCallAdapterFactory)
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
            .create(IpmaClient::class.java)
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

    @Singleton
    @Provides
    fun getForecastsForCityUseCase(ipmaRepository: IpmaRepository): GetForecastsForCityUseCase {
        return GetForecastsForCityUseCase(ipmaRepository)
    }

    @Singleton
    @Provides
    fun getWeatherTypesUseCase(ipmaRepository: IpmaRepository): GetWeatherTypesUseCase {
        return GetWeatherTypesUseCase(ipmaRepository)
    }

    @Singleton
    @Provides
    fun getWindSpeedsUseCase(ipmaRepository: IpmaRepository): GetWindSpeedsUseCase {
        return GetWindSpeedsUseCase(ipmaRepository)
    }

    @Singleton
    @Provides
    fun getForecastsForDayUseCase(ipmaRepository: IpmaRepository): GetForecastsForDayUseCase {
        return GetForecastsForDayUseCase(ipmaRepository)
    }

    @Singleton
    @Provides
    fun getCitiesUseCase(ipmaRepository: IpmaRepository): GetCitiesUseCase {
        return GetCitiesUseCase(ipmaRepository)
    }
}
