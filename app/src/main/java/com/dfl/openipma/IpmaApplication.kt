package com.dfl.openipma

import android.app.Application
import com.dfl.openipma.di.Injector
import com.mapbox.mapboxsdk.Mapbox

class IpmaApplication : Application() {

    val injector: Injector by lazy { Injector(this) }

    override fun onCreate() {
        super.onCreate()
        Mapbox.getInstance(applicationContext, "")
    }
}