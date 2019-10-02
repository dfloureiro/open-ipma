package com.dfl.openipma.di.modules

import com.dfl.openipma.di.scope.FragmentScope
import com.dfl.openipma.home.HomeForecastsAdapter
import com.dfl.openipma.home.HomeFragment
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
