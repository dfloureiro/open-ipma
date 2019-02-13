package com.dfl.openipma.di.modules

import android.app.AlarmManager
import android.app.IntentService
import android.app.NotificationManager
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
    fun alarmManager(@Named("application") context: Context): AlarmManager {
        return context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    @Singleton
    @Provides
    @JvmStatic
    fun notificationManager(@Named("application") context: Context): NotificationManager {
        return context.getSystemService(IntentService.NOTIFICATION_SERVICE) as NotificationManager
    }
}