package com.dfl.openipma.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.bskyb.datapersistance.PersistenceRepositoryImpl
import com.bskyb.domainpersistence.repository.PersistenceRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
object PersistenceDataModule {

    private const val PREFERENCES_FILE = "OPEN_IPMA_PREFERENCES"

    @Singleton
    @Provides
    @JvmStatic
    fun sharedPreferences(@Named("application") context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
    }

    @Reusable
    @Provides
    @JvmStatic
    fun persistenceRepository(sharedPreferences: SharedPreferences): PersistenceRepository {
        return PersistenceRepositoryImpl(sharedPreferences)
    }
}