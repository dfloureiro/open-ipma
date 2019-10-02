package com.dfl.openipma.di.component

import android.app.NotificationManager
import android.app.job.JobScheduler
import com.dfl.domainanalytics.usecase.HandleOnBoardingEvents
import com.dfl.domainanalytics.usecase.HandleOnScreenOpenEvents
import com.dfl.domainanalytics.usecase.HandleOnSettingsChangeEvents
import com.dfl.domainipma.usecase.GetCitiesUseCase
import com.dfl.domainipma.usecase.GetForecastsForCityUseCase
import com.dfl.domainipma.usecase.GetSeismicInfoForAreaIdUseCase
import com.dfl.domainipma.usecase.GetWeatherTypesUseCase
import com.dfl.domainipma.usecase.GetWindSpeedsUseCase
import com.dfl.domainpersistence.usecase.GetWeatherNotificationPreferencesUseCase
import com.dfl.domainpersistence.usecase.HandleFirstLaunchUseCase
import com.dfl.domainpersistence.usecase.HandleLastKnownLocationUseCase
import com.dfl.openipma.ViewModelFactory
import com.dfl.openipma.di.modules.AnalyticsUseCaseModule
import com.dfl.openipma.di.modules.IpmaUseCasesModule
import com.dfl.openipma.di.modules.LocationModule
import com.dfl.openipma.di.modules.PersistenceUseCasesModule
import com.dfl.openipma.di.modules.ServiceModule
import com.dfl.openipma.di.modules.ViewModelFactoryModule
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelFactoryModule::class, LocationModule::class, PersistenceUseCasesModule::class, IpmaUseCasesModule::class, AnalyticsUseCaseModule::class, ServiceModule::class])
interface ApplicationComponent {

    fun fusedLocationProviderClient(): FusedLocationProviderClient

    fun viewModelFactory(): ViewModelFactory

    fun handleFirstLaunchUseCase(): HandleFirstLaunchUseCase

    fun getForecastsForCityUseCase(): GetForecastsForCityUseCase

    fun getWindSpeedsUseCase(): GetWindSpeedsUseCase

    fun getWeatherTypesUseCase(): GetWeatherTypesUseCase

    fun handleLastKnownLocationUseCase(): HandleLastKnownLocationUseCase

    fun getWeatherNotificationPreferencesUseCase(): GetWeatherNotificationPreferencesUseCase

    fun handleOnBoardingEvents(): HandleOnBoardingEvents

    fun handleOnScreenOpenEvents(): HandleOnScreenOpenEvents

    fun handleOnSettingsChangeEvents(): HandleOnSettingsChangeEvents

    fun getCitiesUseCase(): GetCitiesUseCase

    fun getSeismicInfoForAreaIdUseCase(): GetSeismicInfoForAreaIdUseCase

    fun jobScheduler(): JobScheduler

    fun notificationManager(): NotificationManager
}
