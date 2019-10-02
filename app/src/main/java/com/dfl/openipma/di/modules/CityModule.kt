package com.dfl.openipma.di.modules

import com.dfl.openipma.city.CityForecastsAdapter
import com.dfl.openipma.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

@Module
object CityModule {

    @FragmentScope
    @Provides
    @JvmStatic
    fun cityForecastsAdapter() = CityForecastsAdapter()
}
