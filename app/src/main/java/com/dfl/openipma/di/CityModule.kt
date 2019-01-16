package com.dfl.openipma.di

import com.dfl.openipma.CityForecastsAdapter
import dagger.Module
import dagger.Provides

@Module
class CityModule {

    @FragmentScope
    @Provides
    fun cityForecastsAdapter(): CityForecastsAdapter {
        return CityForecastsAdapter()
    }
}