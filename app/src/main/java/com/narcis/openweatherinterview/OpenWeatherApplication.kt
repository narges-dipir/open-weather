package com.narcis.openweatherinterview

import android.app.Application
import android.content.res.Configuration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OpenWeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}