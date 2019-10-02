package com.dfl.openipma.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.dfl.datapersistance.PersistenceRepositoryImpl
import com.dfl.domainpersistence.repository.PersistenceRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
object PersistenceDataModule {

    @Singleton
    @Provides
    @JvmStatic
    fun sharedPreferences(@Named("application") context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Reusable
    @Provides
    @JvmStatic
    fun persistenceRepository(sharedPreferences: SharedPreferences): PersistenceRepository {
        return PersistenceRepositoryImpl(sharedPreferences)
    }
}
