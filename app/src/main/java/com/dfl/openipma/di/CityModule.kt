package com.dfl.openipma.di

import com.dfl.openipma.CityForecastsAdapter
import dagger.Module
import dagger.Provides

@Module
object CityModule {

    @FragmentScope
    @Provides
    @JvmStatic
    fun cityForecastsAdapter() = CityForecastsAdapter()
}