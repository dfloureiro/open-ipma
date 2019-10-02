package com.dfl.openipma.service

import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.dfl.domainipma.usecase.GetCitiesUseCase
import com.dfl.domainipma.usecase.GetForecastsForCityUseCase
import com.dfl.domainipma.usecase.GetWeatherTypesUseCase
import com.dfl.domainipma.usecase.GetWindSpeedsUseCase
import com.dfl.domainpersistence.usecase.GetWeatherNotificationPreferencesUseCase
import com.dfl.domainpersistence.usecase.HandleLastKnownLocationUseCase
import com.dfl.openipma.IpmaApplication
import com.dfl.openipma.R
import com.dfl.openipma.city.CityForecastsActivity
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WeatherNotificationService : IntentService(weatherNotificationService) {

    @Inject
    lateinit var getCitiesUseCase: GetCitiesUseCase
    @Inject
    lateinit var getForecastsForCityUseCase: GetForecastsForCityUseCase
    @Inject
    lateinit var getWindSpeedsUseCase: GetWindSpeedsUseCase
    @Inject
    lateinit var getWeatherTypesUseCase: GetWeatherTypesUseCase
    @Inject
    lateinit var handleLastKnownLocationUseCase: HandleLastKnownLocationUseCase
    @Inject
    lateinit var weatherServiceNotificationContentMapper: WeatherServiceNotificationContentMapper
    @Inject
    lateinit var notificationManager: NotificationManager
    @Inject
    lateinit var getWeatherNotificationPreferencesUseCase: GetWeatherNotificationPreferencesUseCase

    private val viewModelJob = Job()
    val scope = CoroutineScope(Dispatchers.IO + viewModelJob)

    override fun onCreate() {
        super.onCreate()
        (application as IpmaApplication).injector.inject(this)

        createNotificationChannel()

        startForeground(
            weatherNotificationId,
            NotificationCompat.Builder(
                this,
                weatherNotificationChannelId
            ).setContentTitle("").setContentText("").build()
        )
    }

    override fun onHandleIntent(intent: Intent?) {
        if (getWeatherNotificationPreferencesUseCase.isWeatherNotificationEnabled()) {
            scope.launch {
                try {
                    val lastKnownLocationId =
                        handleLastKnownLocationUseCase.getLastKnownTerritoryId()
                    val cities = getCitiesUseCase.buildUseCase()
                    val weatherTypes = getWeatherTypesUseCase.buildUseCase()
                    val forecasts =
                        getForecastsForCityUseCase.buildUseCase(
                            GetForecastsForCityUseCase.Params(
                                lastKnownLocationId
                            )
                        )
                    forecasts.find { it.isToday }?.also {
                        sendNotification(
                            weatherServiceNotificationContentMapper.create(
                                lastKnownLocationId,
                                it,
                                cities,
                                weatherTypes
                            )
                        )
                    }
                } catch (e: Exception) {
                    Log.e("NotificationService", e.localizedMessage.orEmpty())
                } finally {
                    viewModelJob.cancel()
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun sendNotification(notificationContent: NotificationContent) {
        val notificationBuilder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, weatherNotificationChannelId)
        } else {
            Notification.Builder(this)
        }

        val title = String.format(
            getString(R.string.notification_content_title),
            notificationContent.maximumTemperature,
            notificationContent.minimumTemperature,
            notificationContent.cityName
        )

        val notificationIntent = Intent(this, CityForecastsActivity::class.java).also {
            it.putExtra(CityForecastsActivity.CITY_ID_BUNDLE_KEY, notificationContent.cityId)
            it.putExtra(CityForecastsActivity.CITY_NAME_BUNDLE_KEY, notificationContent.cityName)
            it.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val notification = notificationBuilder
            .setContentTitle(title)
            .setContentText(notificationContent.text)
            .setSmallIcon(notificationContent.iconResourceId)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(weatherNotificationId, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.notification_channel_name)
            val mChannel =
                NotificationChannel(
                    weatherNotificationChannelId,
                    name,
                    NotificationManager.IMPORTANCE_LOW
                )
            mChannel.description = getString(R.string.notification_channel_description)
            notificationManager.createNotificationChannel(mChannel)
        }
    }
}
