package com.dfl.openipma.di

import com.bskyb.domainpersistence.repository.PersistenceRepository
import com.bskyb.domainpersistence.usecase.HandleFirstLaunchUseCase
import com.bskyb.domainpersistence.usecase.HandleLastKnownLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module(includes = [PersistenceDataModule::class])
object PersistenceUseCasesModule {

    @Reusable
    @Provides
    @JvmStatic
    fun handleFirstLaunchUseCase(persistenceRepository: PersistenceRepository): HandleFirstLaunchUseCase {
        return HandleFirstLaunchUseCase(persistenceRepository)
    }

    @Reusable
    @Provides
    @JvmStatic
    fun handleLastKnownLocationUseCase(persistenceRepository: PersistenceRepository): HandleLastKnownLocationUseCase {
        return HandleLastKnownLocationUseCase(persistenceRepository)
    }
}