package com.example.wsw.bakingapp.di

import com.example.wsw.bakingapp.ui.RecipeListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by wsw on 17-8-22.
 */
@Module
abstract class RecipeListModule {
  @ContributesAndroidInjector
  abstract fun contributeRecipeListActivity(): RecipeListActivity
}