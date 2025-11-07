package com.igaworks.adbrixkotlinsample

import android.app.Application
import android.util.Log
import com.igaworks.adbrix.Adbrix
import com.igaworks.adbrix.AdbrixConfig

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        val config = AdbrixConfig.Builder()
            .setLogEnable(true)
            .setLogLevel(Log.VERBOSE)
            .setCollectGoogleAdvertisingId(true)
        Adbrix.getInstance().init(
            this,
            BuildConfig.ADBRIX_APPLICATION_KEY,
            BuildConfig.ADBRIX_SECRET_KEY,
            config.build()
        )
    }
}