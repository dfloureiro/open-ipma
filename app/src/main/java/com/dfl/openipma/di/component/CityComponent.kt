package com.dfl.openipma.di.component

import com.dfl.openipma.city.CityForecastsAdapter
import com.dfl.openipma.city.CityForecastsFragment
import com.dfl.openipma.di.modules.CityModule
import com.dfl.openipma.di.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [CityModule::class])
interface CityComponent {

    fun inject(cityForecastsFragment: CityForecastsFragment)

    fun cityForecastsAdapter(): CityForecastsAdapter
}
