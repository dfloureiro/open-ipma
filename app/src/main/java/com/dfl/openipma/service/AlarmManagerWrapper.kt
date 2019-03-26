package com.dfl.openipma.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.dfl.domainpersistence.usecase.GetWeatherNotificationPreferencesUseCase
import dagger.Reusable
import java.util.*
import javax.inject.Inject

@Reusable
class AlarmManagerWrapper @Inject constructor(
    private val alarmManager: AlarmManager,
    private val getWeatherNotificationPreferencesUseCase: GetWeatherNotificationPreferencesUseCase
) {

    fun scheduleAlarmWeatherService(context: Context) {

        val notificationHour = getWeatherNotificationPreferencesUseCase.getHourForWeatherNotification()
        val timeInMillisToTriggerAlarm = GregorianCalendar.getInstance()
            .let {
                if (isNotificationHourBeforeCurrentTime(notificationHour)) {
                    it.add(GregorianCalendar.DATE, 1)
                }
                it.set(GregorianCalendar.HOUR_OF_DAY, notificationHour)
                it.set(GregorianCalendar.MINUTE, ALARM_NOTIFICATION_MINUTES)
                it.set(GregorianCalendar.SECOND, ALARM_NOTIFICATION_SECONDS)
                it.timeInMillis
            }

        val weatherServicePendingIntent = weatherServicePendingIntent(context)
        alarmManager.cancel(weatherServicePendingIntent)

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                timeInMillisToTriggerAlarm,
                weatherServicePendingIntent
            )
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                timeInMillisToTriggerAlarm,
                weatherServicePendingIntent
            )
            else -> alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillisToTriggerAlarm, weatherServicePendingIntent)
        }
    }

    private fun weatherServicePendingIntent(context: Context): PendingIntent {
        return Intent(context, WeatherServiceReceiver::class.java).let {
            PendingIntent.getBroadcast(
                context,
                WEATHER_NOTIFICATION_SERVICE_ID,
                it,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
        }
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
        private const val WEATHER_NOTIFICATION_SERVICE_ID = 123451
        private const val ALARM_NOTIFICATION_MINUTES = 0
        private const val ALARM_NOTIFICATION_SECONDS = 0
    }
}