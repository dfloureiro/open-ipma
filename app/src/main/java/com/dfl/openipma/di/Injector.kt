package com.dfl.openipma.di

import com.dfl.openipma.IpmaApplication
import com.dfl.openipma.city.CityForecastsFragment
import com.dfl.openipma.di.component.*
import com.dfl.openipma.di.modules.ContextModule
import com.dfl.openipma.di.modules.HomeModule
import com.dfl.openipma.seismic.SeismicFragment
import com.dfl.openipma.home.HomeFragment
import com.dfl.openipma.onboarding.OnBoardingActivity
import com.dfl.openipma.service.ServiceStarterJobService
import com.dfl.openipma.service.WeatherNotificationService
import com.dfl.openipma.settings.SettingsFragment

class Injector(ipmaApplication: IpmaApplication) {

    private val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .contextModule(ContextModule(ipmaApplication))
            .build()
    }

    fun inject(homeFragment: HomeFragment) {
        DaggerHomeComponent.builder()
            .applicationComponent(applicationComponent)
            .homeModule(HomeModule(homeFragment))
            .build()
            .inject(homeFragment)
    }

    fun inject(cityForecastsFragment: CityForecastsFragment) {
        DaggerCityComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
            .inject(cityForecastsFragment)
    }

    fun inject(onBoardingActivity: OnBoardingActivity) {
        DaggerOnBoardingComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
            .inject(onBoardingActivity)
    }

    fun inject(weatherNotificationService: WeatherNotificationService) {
        DaggerWeatherServiceComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
            .inject(weatherNotificationService)
    }

    fun inject(serviceStarterJobService: ServiceStarterJobService) {
        DaggerJobServiceComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
            .inject(serviceStarterJobService)
    }

    fun inject(settingsFragment: SettingsFragment) {
        DaggerSettingsComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
            .inject(settingsFragment)
    }

    fun inject(seismicFragment: SeismicFragment) {
        DaggerEarthquakeComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
            .inject(seismicFragment)
    }
}