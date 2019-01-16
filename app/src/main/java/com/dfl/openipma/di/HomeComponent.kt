package com.dfl.openipma.di

import com.dfl.openipma.HomeForecastsAdapter
import com.dfl.openipma.HomeFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [HomeModule::class])
interface HomeComponent {

    fun inject(homeFragment: HomeFragment)

    fun homeForecastsAdapter(): HomeForecastsAdapter
}