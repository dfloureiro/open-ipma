package com.dfl.openipma.di

import com.dfl.domainipma.usecase.GetCitiesUseCase
import com.dfl.domainipma.usecase.GetForecastsForCityUseCase
import com.dfl.openipma.HomeFragment
import com.dfl.openipma.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [IpmaModule::class, ViewModelFactoryModule::class])
interface ApplicationComponent {

    fun inject(homeFragment: HomeFragment)

    fun forecastsForCityUseCase(): GetForecastsForCityUseCase

    fun getCitiesUseCase(): GetCitiesUseCase
}