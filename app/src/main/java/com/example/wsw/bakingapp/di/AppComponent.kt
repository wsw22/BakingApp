package com.example.wsw.bakingapp.di

import android.app.Application
import com.example.wsw.bakingapp.MyApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by wsw on 17-8-22.
 */
@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, ViewModelModule::class))
interface AppComponent {
  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }

  fun inject(myApp: MyApp)
}