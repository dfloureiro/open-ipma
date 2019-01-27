package com.dfl.openipma.di.modules

import com.dfl.domainanalytics.repository.AnalyticsRepository
import com.dfl.domainanalytics.usecase.HandleOnBoardingEvents
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
}