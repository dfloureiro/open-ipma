package com.dfl.openipma.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.JobIntentService
import android.util.Log
import com.bskyb.domainpersistence.usecase.HandleLastKnownLocationUseCase
import com.dfl.domainipma.usecase.GetForecastsForCityUseCase
import com.dfl.domainipma.usecase.GetWeatherTypesUseCase
import com.dfl.domainipma.usecase.GetWindSpeedsUseCase
import com.dfl.openipma.IpmaApplication
import com.dfl.openipma.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


class WeatherNotificationJob : JobIntentService() {

    @Inject
    lateinit var getForecastsForCityUseCase: GetForecastsForCityUseCase
    @Inject
    lateinit var getWindSpeedsUseCase: GetWindSpeedsUseCase
    @Inject
    lateinit var getWeatherTypesUseCase: GetWeatherTypesUseCase
    @Inject
    lateinit var handleLastKnownLocationUseCase: HandleLastKnownLocationUseCase

    private val viewModelJob = Job()
    val scope = CoroutineScope(Dispatchers.IO + viewModelJob)

    override fun onCreate() {
        super.onCreate()
        (application as IpmaApplication).injector.inject(this)
    }

    override fun onHandleWork(p0: Intent) {

        scope.launch {
            try {
                val lastKnownLocationId = handleLastKnownLocationUseCase.getLastKnownTerritoryId()
                val windSpeeds = getWindSpeedsUseCase.buildUseCase()
                val weatherTypes = getWeatherTypesUseCase.buildUseCase()
                val forecasts =
                    getForecastsForCityUseCase.buildUseCase(GetForecastsForCityUseCase.Params(lastKnownLocationId))
                val todayForecast = forecasts.find { it.isToday }
                if (todayForecast != null) {
                    sendNotification(todayForecast.weatherType.toString(), todayForecast.forecastDate)
                }
            } catch (e: Exception) {
                Log.e("DEU MERDA", "")
            } finally {
                viewModelJob.cancel()
            }
        }
    }

    private fun sendNotification(name: String, description: String) {

        //val intent = Intent(this, ::class.java)
        //val pIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val notificationBuilder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "weather notification"
            val descriptionText = "notification about weather forecasts and reminders"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(WEATHER_NOTIFICATION_CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)


            Notification.Builder(
                this,
                WEATHER_NOTIFICATION_CHANNEL_ID
            )
        } else {
            Notification.Builder(this)
        }
        val notification = notificationBuilder
            .setContentTitle(name)
            .setContentText(description)
            .setSmallIcon(R.drawable.ic_lightning_rainy)
            .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(WEATHER_NOTIFICATION_ID, notification)
    }

    override fun onStopCurrentWork(): Boolean {
        schedule(this.applicationContext)
        return super.onStopCurrentWork()
    }

    companion object {
        private const val WEATHER_NOTIFICATION_JOB_ID = 1000
        private const val WEATHER_NOTIFICATION_ID = 1001
        private const val WEATHER_NOTIFICATION_CHANNEL_ID = "WeatherNotificationJob"
        private const val ESTIMATED_DOWNLOAD_BYTES: Long = 68000
        private const val ESTIMATED_UPLOAD_BYTES: Long = 0

        fun schedule(context: Context) {
            val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            val tomorrowCalendar = GregorianCalendar()
                .also {
                    it.add(Calendar.DATE, 1)
                    it.set(Calendar.HOUR_OF_DAY, 9)
                }
            val todayCalendar = GregorianCalendar()
            val timeUntilTriggerJob = tomorrowCalendar.timeInMillis - todayCalendar.timeInMillis

            val component = ComponentName(context, WeatherNotificationJob::class.java)
            val builder = JobInfo.Builder(WEATHER_NOTIFICATION_JOB_ID, component)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setOverrideDeadline(timeUntilTriggerJob)
                .setPersisted(true)

            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> builder.setEstimatedNetworkBytes(
                    ESTIMATED_DOWNLOAD_BYTES,
                    ESTIMATED_UPLOAD_BYTES
                )
            }

            jobScheduler.schedule(builder.build())
        }
    }
}