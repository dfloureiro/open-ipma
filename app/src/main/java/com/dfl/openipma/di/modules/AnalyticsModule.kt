package com.dfl.openipma.di.modules

import android.content.Context
import com.dfl.dataanalytics.AnalyticsRepositoryImpl
import com.dfl.dataanalytics.wrapper.FirebaseAnalyticsWrapper
import com.dfl.domainanalytics.repository.AnalyticsRepository
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ContextModule::class, PersistenceUseCasesModule::class])
object AnalyticsModule {

    @Singleton
    @Provides
    @JvmStatic
    fun firebaseAnalytics(@Named("application") context: Context): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(context)
    }

    @Reusable
    @Provides
    @JvmStatic
    fun firebaseAnalyticsWrapper(firebaseAnalytics: FirebaseAnalytics): FirebaseAnalyticsWrapper {
        return FirebaseAnalyticsWrapper(firebaseAnalytics)
    }

    @Reusable
    @Provides
    @JvmStatic
    fun analyticsRepositoryImpl(firebaseAnalyticsWrapper: FirebaseAnalyticsWrapper, @Named("analytics_status") analyticsStatus: Boolean): AnalyticsRepository {
        return AnalyticsRepositoryImpl(firebaseAnalyticsWrapper).also { it.setStatus(analyticsStatus) }
    }
}
