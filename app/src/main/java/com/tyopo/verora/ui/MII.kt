package com.tyopo.verora.ui

import android.app.Application
import timber.log.Timber

class MII : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}