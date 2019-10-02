package com.dfl.openipma.di.component

import com.dfl.openipma.di.modules.SeismicModule
import com.dfl.openipma.di.scope.FragmentScope
import com.dfl.openipma.seismic.SeismicAdapter
import com.dfl.openipma.seismic.SeismicFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [SeismicModule::class])
interface EarthquakeComponent {

    fun inject(seismicFragment: SeismicFragment)

    fun seismicAdapter(): SeismicAdapter
}
