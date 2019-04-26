package com.dfl.openipma.di.component

import android.app.NotificationManager
import android.app.job.JobScheduler
import com.dfl.domainanalytics.usecase.HandleOnBoardingEvents
import com.dfl.domainanalytics.usecase.HandleOnScreenOpenEvents
import com.dfl.domainanalytics.usecase.HandleOnSettingsChangeEvents
import com.dfl.domainipma.usecase.*
import com.dfl.domainpersistence.usecase.GetWeatherNotificationPreferencesUseCase
import com.dfl.domainpersistence.usecase.HandleFirstLaunchUseCase
import com.dfl.domainpersistence.usecase.HandleLastKnownLocationUseCase
import com.dfl.openipma.ViewModelFactory
import com.dfl.openipma.di.modules.*
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