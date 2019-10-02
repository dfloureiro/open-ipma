package com.dfl.openipma.di.modules

import com.dfl.openipma.di.scope.FragmentScope
import com.dfl.openipma.seismic.MapFragment
import com.dfl.openipma.seismic.SeismicAdapter
import dagger.Module
import dagger.Provides

@Module
class SeismicModule(private val mapFragment: MapFragment) {

    @FragmentScope
    @Provides
    fun seismicAdapter() = SeismicAdapter(mapFragment)
}
