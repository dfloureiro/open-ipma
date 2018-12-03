package com.dfl.openipma.di

import com.dfl.openipma.CityForecastsFragment
import com.dfl.openipma.HomeFragment

class Injector {

    private lateinit var applicationComponent: ApplicationComponent

    fun inject(homeFragment: HomeFragment) {
        applicationComponent = DaggerApplicationComponent.builder()
            .networkModule(NetworkModule())
            .ipmaModule(IpmaModule())
            .build()
            .also { it.inject(homeFragment) }
    }

    fun inject(cityForecastsFragment: CityForecastsFragment) {
        applicationComponent.inject(cityForecastsFragment)
    }
}