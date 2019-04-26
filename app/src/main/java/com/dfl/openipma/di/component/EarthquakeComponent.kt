package com.dfl.openipma.di.component

import com.dfl.openipma.di.scope.FragmentScope
import com.dfl.openipma.earthquake.EarthquakesFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [])
interface EarthquakeComponent {

    fun inject(earthquakesFragment: EarthquakesFragment)
}