package com.example.wsw.bakingapp.di

import android.app.Application
import android.arch.lifecycle.ViewModel
import com.example.wsw.bakingapp.MyApp
import com.example.wsw.bakingapp.viewModel.RecipeDetailViewModel
import com.example.wsw.bakingapp.viewModel.RecipeListViewModel
import com.example.wsw.bakingapp.viewModel.StepDetailViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by wsw on 17-8-22.
 */
@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class))
interface AppComponent {
  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }

  fun inject(myApp: MyApp)

  fun inject(viewModel: RecipeListViewModel)
  fun inject(viewModel: RecipeDetailViewModel)
  fun inject(viewModel: StepDetailViewModel)
//  fun inject(viewModel: ViewModel)
}