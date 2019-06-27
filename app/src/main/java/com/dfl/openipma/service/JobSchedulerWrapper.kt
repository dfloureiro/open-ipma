package com.dfl.openipma.service

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import com.dfl.domainpersistence.usecase.GetWeatherNotificationPreferencesUseCase
import dagger.Reusable
import java.util.*
import javax.inject.Inject

@Reusable
class JobSchedulerWrapper @Inject constructor(
    private val jobScheduler: JobScheduler,
    private val getWeatherNotificationPreferencesUseCase: GetWeatherNotificationPreferencesUseCase
) {

    fun scheduleAlarmWeatherService(context: Context) {

        val notificationHour = getWeatherNotificationPreferencesUseCase.getHourForWeatherNotification()
        val currentTimeInMillis = GregorianCalendar.getInstance().timeInMillis
        val timeInMillisToTriggerAlarm = GregorianCalendar.getInstance()
            .let {
                if (isNotificationHourBeforeCurrentTime(notificationHour)) {
                    it.add(GregorianCalendar.DATE, 1)
                }
                it.set(GregorianCalendar.HOUR_OF_DAY, notificationHour)
                it.set(GregorianCalendar.MINUTE, ALARM_NOTIFICATION_MINUTES)
                it.set(GregorianCalendar.SECOND, ALARM_NOTIFICATION_SECONDS)
                it.timeInMillis
            } - currentTimeInMillis

        val serviceComponent = ComponentName(context, ServiceStarterJobService::class.java)
        val builder = JobInfo.Builder(WEATHER_NOTIFICATION_JOB_ID, serviceComponent)
            .setMinimumLatency(timeInMillisToTriggerAlarm)
            .setOverrideDeadline(ALARM_DEADLINE_MARGIN + timeInMillisToTriggerAlarm)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setPersisted(true)
            .setRequiresCharging(false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            builder.setEstimatedNetworkBytes(EXPECTED_DOWNLOAD_BYTES, EXPECTED_UPLOAD_BYTES)
        }

        jobScheduler.schedule(builder.build())
    }

    private fun isNotificationHourBeforeCurrentTime(notificationHour: Int): Boolean {
        val timeOfTheAlarmAsToday = GregorianCalendar.getInstance()
            .let {
                it.set(GregorianCalendar.HOUR_OF_DAY, notificationHour)
                it.set(GregorianCalendar.MINUTE, ALARM_NOTIFICATION_MINUTES)
                it.set(GregorianCalendar.SECOND, ALARM_NOTIFICATION_SECONDS)
                it.timeInMillis
            }
        return timeOfTheAlarmAsToday < GregorianCalendar.getInstance().timeInMillis
    }

    companion object {
        private const val WEATHER_NOTIFICATION_JOB_ID = 123451
        private const val ALARM_NOTIFICATION_MINUTES = 0
        private const val ALARM_NOTIFICATION_SECONDS = 0
        private const val ALARM_DEADLINE_MARGIN = 5 * 60 * 1000
        private const val EXPECTED_DOWNLOAD_BYTES: Long = 34590
        private const val EXPECTED_UPLOAD_BYTES: Long = 0
    }
}