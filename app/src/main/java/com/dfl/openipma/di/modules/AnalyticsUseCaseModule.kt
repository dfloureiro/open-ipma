package com.dfl.openipma.di.modules

import com.dfl.domainanalytics.repository.AnalyticsRepository
import com.dfl.domainanalytics.usecase.HandleOnBoardingEvents
import com.dfl.domainanalytics.usecase.HandleOnScreenOpenEvents
import com.dfl.domainanalytics.usecase.HandleOnSettingsChangeEvents
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module(includes = [AnalyticsModule::class])
object AnalyticsUseCaseModule {

    @Reusable
    @Provides
    @JvmStatic
    fun handleOnBoardingEvents(analyticsRepository: AnalyticsRepository): HandleOnBoardingEvents {
        return HandleOnBoardingEvents(analyticsRepository)
    }

    @Reusable
    @Provides
    @JvmStatic
    fun handleOnScreenOpenEvents(analyticsRepository: AnalyticsRepository): HandleOnScreenOpenEvents {
        return HandleOnScreenOpenEvents(analyticsRepository)
    }

    @Reusable
    @Provides
    @JvmStatic
    fun handleOnSettingsChangeEvents(analyticsRepository: AnalyticsRepository): HandleOnSettingsChangeEvents {
        return HandleOnSettingsChangeEvents(analyticsRepository)
    }
}