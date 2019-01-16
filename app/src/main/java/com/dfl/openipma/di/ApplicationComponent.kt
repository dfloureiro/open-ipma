package com.dfl.openipma.di

import com.bskyb.domainpersistence.usecase.HandleFirstLaunchUseCase
import com.dfl.openipma.ViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelFactoryModule::class, LocationModule::class, PersistenceModule::class, IpmaUseCasesModule::class])
interface ApplicationComponent {

    fun fusedLocationProviderClient(): FusedLocationProviderClient

    fun viewModelFactory(): ViewModelFactory

    fun handleFirstLaunchUseCase(): HandleFirstLaunchUseCase
}