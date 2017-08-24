package com.example.wsw.bakingapp.di

import com.example.wsw.bakingapp.ui.RecipeDetailActivity
import com.example.wsw.bakingapp.ui.RecipeListActivity
import com.example.wsw.bakingapp.ui.step.StepDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by wsw on 17-8-24.
 */
@Module
abstract class ActivityBuilder {
  @ContributesAndroidInjector
  abstract fun contributeRecipeListActivity(): RecipeListActivity

  @ContributesAndroidInjector(modules = arrayOf(RecipeDetailFragmentBuilder::class))
  abstract fun contributeRecipeDetailActivity(): RecipeDetailActivity

  @ContributesAndroidInjector(modules = arrayOf(RecipeDetailFragmentBuilder::class))
  abstract fun contributeStepDetailActivity(): StepDetailActivity
}