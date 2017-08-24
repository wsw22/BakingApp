package com.example.wsw.bakingapp

import android.app.Activity
import android.app.Application
import com.example.wsw.bakingapp.di.AppComponent
import com.example.wsw.bakingapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

/**
 * Created by wsw on 17-8-21.
 */
class MyApp : Application(), HasActivityInjector {
  @Inject
  lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

  lateinit var appComponent: AppComponent

  override fun onCreate() {
    super.onCreate()

    appComponent = DaggerAppComponent.builder().application(this).build()

    appComponent.inject(this)

    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }
  }

  override fun activityInjector() = dispatchingActivityInjector
}