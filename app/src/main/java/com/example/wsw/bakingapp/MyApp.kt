package com.example.wsw.bakingapp

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by wsw on 17-8-21.
 */
class MyApp : Application() {
  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }
  }
}