package com.dfl.openipma

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
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

    fun schedule(context: Context) {
        val component = ComponentName(context, WeatherNotificationJob::class.java)
        val builder = JobInfo.Builder(WEATHER_NOTIFICATION_JOB_ID, component)
            // schedule it to run any time between 1 - 5 minutes
            .setMinimumLatency(ONE_MIN.toLong())
            .setOverrideDeadline((5 * ONE_MIN).toLong())

        val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(builder.build())
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


            Notification.Builder(this, WEATHER_NOTIFICATION_CHANNEL_ID)
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

    override fun onDestroy() {
        super.onDestroy()
        //viewModelJob.cancel()
    }

    companion object {
        private const val WEATHER_NOTIFICATION_JOB_ID = 1000
        private const val WEATHER_NOTIFICATION_ID = 1001
        private const val WEATHER_NOTIFICATION_CHANNEL_ID = "WeatherNotificationJob"
        private const val ONE_MIN = 60 * 1000

        fun enqueueWork(context: Context, work: Intent) {
            enqueueWork(context, WeatherNotificationJob::class.java, WEATHER_NOTIFICATION_JOB_ID, work)
        }
    }
}