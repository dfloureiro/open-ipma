package com.dfl.openipma.di

import com.bskyb.domainpersistence.repository.PersistenceRepository
import com.bskyb.domainpersistence.usecase.HandleFirstLaunchUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [PersistenceDataModule::class])
class PersistenceUseCasesModule {

    @Singleton
    @Provides
    fun handleFirstLaunchUseCase(persistenceRepository: PersistenceRepository): HandleFirstLaunchUseCase {
        return HandleFirstLaunchUseCase(persistenceRepository)
    }
}