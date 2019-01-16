package com.dfl.openipma.di

import com.dfl.openipma.CityForecastsAdapter
import com.dfl.openipma.CityForecastsFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [CityModule::class])
interface CityComponent {

    fun inject(cityForecastsFragment: CityForecastsFragment)

    fun cityForecastsAdapter(): CityForecastsAdapter

}