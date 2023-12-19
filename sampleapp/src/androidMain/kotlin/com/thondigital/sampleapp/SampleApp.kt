package com.thondigital.sampleapp

import Kmule.startKmule
import android.app.Application
import android.content.Context

class SampleApp : Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
        startKmule { appContext }
    }
}
