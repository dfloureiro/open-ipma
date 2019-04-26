package com.dfl.openipma.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.dfl.openipma.ViewModelFactory
import com.dfl.openipma.city.CityForecastsViewModel
import com.dfl.openipma.home.HomeViewModel
import com.dfl.openipma.seismic.SeismicViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module
abstract class ViewModelFactoryModule {

    @Singleton
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(myViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CityForecastsViewModel::class)
    abstract fun bindCityForecastsViewModel(myViewModel: CityForecastsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SeismicViewModel::class)
    abstract fun bindSeismicViewModel(myViewModel: SeismicViewModel): ViewModel
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)