package com.dfl.openipma.di.modules

import com.bskyb.domainpersistence.repository.PersistenceRepository
import com.bskyb.domainpersistence.usecase.GetWeatherNotificationPreferencesUseCase
import com.bskyb.domainpersistence.usecase.HandleFirstLaunchUseCase
import com.bskyb.domainpersistence.usecase.HandleLastKnownLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Named

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

    @Reusable
    @Provides
    @JvmStatic
    fun getWeatherNotificationPreferencesUseCase(persistenceRepository: PersistenceRepository): GetWeatherNotificationPreferencesUseCase {
        return GetWeatherNotificationPreferencesUseCase(persistenceRepository)
    }

    @Named("analytics_status")
    @Provides
    @JvmStatic
    fun getAnalyticsStatus(getWeatherNotificationPreferencesUseCase: GetWeatherNotificationPreferencesUseCase): Boolean {
        return getWeatherNotificationPreferencesUseCase.getAnalyticsStatus()
    }
}