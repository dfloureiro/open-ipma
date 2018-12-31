package com.dfl.openipma.di

import com.dfl.domainipma.usecase.GetCitiesUseCase
import com.dfl.domainipma.usecase.GetForecastsForCityUseCase
import com.dfl.domainipma.usecase.GetWeatherTypesUseCase
import com.dfl.domainipma.usecase.GetWindSpeedsUseCase
import com.dfl.openipma.CityForecastsFragment
import com.dfl.openipma.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [IpmaModule::class, ViewModelFactoryModule::class])
interface ApplicationComponent {

    fun inject(homeFragment: HomeFragment)

    fun inject(cityForecastsFragment: CityForecastsFragment)

    fun forecastsForCityUseCase(): GetForecastsForCityUseCase

    fun getCitiesUseCase(): GetCitiesUseCase

    fun getWindSpeedsUseCase(): GetWindSpeedsUseCase

    fun getWeatherTypesUseCase(): GetWeatherTypesUseCase
}