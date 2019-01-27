package com.dfl.openipma.di.component

import com.dfl.openipma.di.modules.CityModule
import com.dfl.openipma.di.scope.FragmentScope
import com.dfl.openipma.service.WeatherNotificationJob
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [CityModule::class])
interface WeatherJobComponent {

    fun inject(weatherNotificationJob: WeatherNotificationJob)
}