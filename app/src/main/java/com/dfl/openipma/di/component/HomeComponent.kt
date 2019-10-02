package com.dfl.openipma.di.component

import com.dfl.openipma.di.modules.HomeModule
import com.dfl.openipma.di.scope.FragmentScope
import com.dfl.openipma.home.HomeForecastsAdapter
import com.dfl.openipma.home.HomeFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [HomeModule::class])
interface HomeComponent {

    fun inject(homeFragment: HomeFragment)

    fun homeForecastsAdapter(): HomeForecastsAdapter
}
