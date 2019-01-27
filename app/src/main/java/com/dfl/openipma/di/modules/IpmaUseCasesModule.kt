package com.dfl.openipma.di.modules

import com.dfl.domainipma.repository.IpmaRepository
import com.dfl.domainipma.repository.LocationRepository
import com.dfl.domainipma.usecase.*
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module(includes = [IpmaDataModule::class])
object IpmaUseCasesModule {

    @Reusable
    @Provides
    @JvmStatic
    fun getForecastsForCityUseCase(ipmaRepository: IpmaRepository): GetForecastsForCityUseCase {
        return GetForecastsForCityUseCase(ipmaRepository)
    }

    @Reusable
    @Provides
    @JvmStatic
    fun getWeatherTypesUseCase(ipmaRepository: IpmaRepository): GetWeatherTypesUseCase {
        return GetWeatherTypesUseCase(ipmaRepository)
    }

    @Reusable
    @Provides
    @JvmStatic
    fun getWindSpeedsUseCase(ipmaRepository: IpmaRepository): GetWindSpeedsUseCase {
        return GetWindSpeedsUseCase(ipmaRepository)
    }

    @Reusable
    @Provides
    @JvmStatic
    fun getForecastsForDayUseCase(ipmaRepository: IpmaRepository): GetForecastsForDayUseCase {
        return GetForecastsForDayUseCase(ipmaRepository)
    }

    @Reusable
    @Provides
    @JvmStatic
    fun getCitiesUseCase(ipmaRepository: IpmaRepository): GetCitiesUseCase {
        return GetCitiesUseCase(ipmaRepository)
    }

    @Reusable
    @Provides
    @JvmStatic
    fun getClosestCityUseCase(locationRepository: LocationRepository): GetClosestCityUseCase {
        return GetClosestCityUseCase(locationRepository)
    }
}