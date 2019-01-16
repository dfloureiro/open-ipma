package com.dfl.openipma.di

import com.dfl.openipma.HomeForecastsAdapter
import com.dfl.openipma.HomeFragment
import dagger.Module
import dagger.Provides

@Module
class HomeModule(private val homeFragment: HomeFragment) {

    @FragmentScope
    @Provides
    fun homeForecastsAdapter(): HomeForecastsAdapter {
        return HomeForecastsAdapter(homeFragment)
    }
}