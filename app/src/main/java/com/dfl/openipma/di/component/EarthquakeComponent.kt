package com.dfl.openipma.di.component

import com.dfl.openipma.di.scope.FragmentScope
import com.dfl.openipma.seismic.SeismicFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class])
interface EarthquakeComponent {

    fun inject(seismicFragment: SeismicFragment)
}