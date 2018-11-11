package com.dfl.openipma

import android.app.Application
import com.dfl.openipma.di.Injector

class IpmaApplication : Application() {

    val injector = Injector()
}