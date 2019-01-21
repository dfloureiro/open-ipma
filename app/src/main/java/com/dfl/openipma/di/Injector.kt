package com.dfl.openipma.di

import com.dfl.openipma.*

class Injector {

    private lateinit var applicationComponent: ApplicationComponent

    fun inject(ipmaApplication: IpmaApplication) {
        applicationComponent = DaggerApplicationComponent.builder()
            .networkModule(NetworkModule())
            .ipmaDataModule(IpmaDataModule())
            .ipmaUseCasesModule(IpmaUseCasesModule())
            .persistenceDataModule(PersistenceDataModule(ipmaApplication))
            .persistenceUseCasesModule(PersistenceUseCasesModule())
            .locationModule(LocationModule(ipmaApplication))
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
            .cityModule(CityModule())
            .build()
            .inject(cityForecastsFragment)
    }

    fun inject(onBoardingActivity: OnBoardingActivity) {
        DaggerOnBoardingComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
            .inject(onBoardingActivity)
    }

    fun inject(weatherNotificationJob: WeatherNotificationJob) {
        DaggerWeatherJobComponent.builder()
            .applicationComponent(DaggerApplicationComponent.builder()
                .networkModule(NetworkModule())
                .ipmaDataModule(IpmaDataModule())
                .ipmaUseCasesModule(IpmaUseCasesModule())
                .persistenceDataModule(PersistenceDataModule(weatherNotificationJob.application))
                .persistenceUseCasesModule(PersistenceUseCasesModule())
                .locationModule(LocationModule(weatherNotificationJob.application))
                .build())
            .cityModule(CityModule())
            .build()
            .inject(weatherNotificationJob)
    }
}