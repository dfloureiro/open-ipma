package com.dfl.openipma.di.component

import com.dfl.openipma.city.CityForecastsActivity
import com.dfl.openipma.di.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class])
interface CityForecastsActivityComponent {

    fun inject(cityForecastsActivity: CityForecastsActivity)
}