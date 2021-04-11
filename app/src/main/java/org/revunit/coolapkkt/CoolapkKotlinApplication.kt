package org.revunit.coolapkkt

import android.app.Application
import android.content.Context

class CoolapkKotlinApplication : Application() {
    companion object {
        lateinit var myApplicationContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        myApplicationContext = applicationContext
    }
}