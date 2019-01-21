package com.dfl.openipma.di

import com.dfl.openipma.WeatherNotificationJob
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [CityModule::class])
interface WeatherJobComponent {

    fun inject(weatherNotificationJob: WeatherNotificationJob)
}