package com.dfl.openipma.di

import com.dfl.domainipma.repository.IpmaRepository
import com.dfl.domainipma.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [IpmaDataModule::class])
class IpmaUseCasesModule {

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