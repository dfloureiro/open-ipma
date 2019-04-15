package com.dfl.openipma.di.modules

import android.app.IntentService
import android.app.NotificationManager
import android.app.job.JobScheduler
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
object ServiceModule {

    @Singleton
    @Provides
    @JvmStatic
    fun jobScheduler(@Named("application") context: Context): JobScheduler {
        return context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
    }

    @Singleton
    @Provides
    @JvmStatic
    fun notificationManager(@Named("application") context: Context): NotificationManager {
        return context.getSystemService(IntentService.NOTIFICATION_SERVICE) as NotificationManager
    }
}