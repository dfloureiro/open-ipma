package com.dfl.openipma.home

import android.content.Intent
import android.os.Bundle
import com.dfl.openipma.R
import com.dfl.openipma.base.OptionsMenuActivity
import com.dfl.openipma.service.WeatherNotificationJob

class HomeActivity : OptionsMenuActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)

        WeatherNotificationJob.enqueueWork(this, Intent())

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, HomeFragment.newInstance())
                .commit()
        }
    }
}