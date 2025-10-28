package com.igaworks.adbrixjavasample;

import android.app.Application;
import android.util.Log;

import com.igaworks.adbrix.Adbrix;
import com.igaworks.adbrix.AdbrixConfig;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AdbrixConfig.Builder config = new AdbrixConfig.Builder()
            .setLogEnable(true)
            .setLogLevel(Log.VERBOSE)
            .setCollectGoogleAdvertisingId(true);
        Adbrix.getInstance().init(this, BuildConfig.ADBRIX_APPLICATION_KEY, BuildConfig.ADBRIX_SECRET_KEY, config.build());
    }
}
