package com.dfl.openipma.home

import android.os.Bundle
import com.dfl.openipma.R
import com.dfl.openipma.base.OptionsMenuActivity
import com.dfl.openipma.service.WeatherNotificationJob

class HomeActivity : OptionsMenuActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)

        if (savedInstanceState == null) {
            scheduleWeatherNotificationJob()
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, HomeFragment.newInstance())
                .commit()
        }
    }

    private fun scheduleWeatherNotificationJob() {
        WeatherNotificationJob.schedule(this.applicationContext)
    }
}