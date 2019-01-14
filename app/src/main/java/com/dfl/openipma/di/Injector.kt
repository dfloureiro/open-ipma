package com.dfl.openipma.di

import com.dfl.openipma.CityForecastsFragment
import com.dfl.openipma.HomeFragment
import com.dfl.openipma.IpmaApplication

class Injector {

    private lateinit var applicationComponent: ApplicationComponent

    fun inject(ipmaApplication: IpmaApplication) {
        applicationComponent = DaggerApplicationComponent.builder()
            .networkModule(NetworkModule())
            .ipmaModule(IpmaModule())
            .locationModule(LocationModule(ipmaApplication))
            .build()
    }

    fun inject(homeFragment: HomeFragment) {
        applicationComponent.inject(homeFragment)
    }

    fun inject(cityForecastsFragment: CityForecastsFragment) {
        applicationComponent.inject(cityForecastsFragment)
    }
}