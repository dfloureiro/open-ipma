package com.dfl.openipma.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class WeatherServiceReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.startService(Intent(context, WeatherNotificationService::class.java))
    }
}