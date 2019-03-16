package com.payback.pixabay.timber;

import android.app.Application;

import timber.log.Timber;

public class LogApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
            Timber.plant(new Timber.DebugTree());
    }
}
