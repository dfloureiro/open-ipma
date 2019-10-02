package com.dfl.openipma.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.Build
import com.dfl.openipma.IpmaApplication
import javax.inject.Inject

class ServiceStarterJobService : JobService() {

    @Inject
    lateinit var jobSchedulerWrapper: JobSchedulerWrapper

    override fun onCreate() {
        super.onCreate()
        (application as IpmaApplication).injector.inject(this)
    }

    override fun onStartJob(params: JobParameters): Boolean {
        val service = Intent(applicationContext, WeatherNotificationService::class.java)
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> applicationContext.startForegroundService(
                service
            )
            else -> applicationContext.startService(service)
        }
        jobSchedulerWrapper.scheduleAlarmWeatherService(applicationContext)
        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        return true
    }
}
