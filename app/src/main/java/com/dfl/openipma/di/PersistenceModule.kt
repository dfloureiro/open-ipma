package com.dfl.openipma.di

import android.content.Context
import android.content.SharedPreferences
import com.bskyb.datapersistance.PersistenceRepositoryImpl
import com.bskyb.domainpersistence.repository.PersistenceRepository
import com.bskyb.domainpersistence.usecase.HandleFirstLaunchUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule(private val context: Context) {

    @Singleton
    @Provides
    fun sharedPreferences(): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun persistenceRepository(sharedPreferences: SharedPreferences): PersistenceRepository {
        return PersistenceRepositoryImpl(sharedPreferences)
    }

    //TODO should move this for the onboarding?
    @Singleton
    @Provides
    fun handleFirstLaunchUseCase(persistenceRepository: PersistenceRepository): HandleFirstLaunchUseCase {
        return HandleFirstLaunchUseCase(persistenceRepository)
    }

    companion object {
        private const val PREFERENCES_FILE = "OPEN_IPMA_PREFERENCES"
    }
}