package com.example.wsw.bakingapp.di

import com.example.wsw.bakingapp.ui.ingredient.IngredientListFragment
import com.example.wsw.bakingapp.ui.step.StepDetailFragment
import com.example.wsw.bakingapp.ui.step.StepListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by wsw on 17-8-24.
 */
@Module
abstract class RecipeDetailFragmentBuilder {
  @ContributesAndroidInjector
  abstract fun contributeIngredientListFragment(): IngredientListFragment

  @ContributesAndroidInjector
  abstract fun contributeStepListFragment(): StepListFragment

  @ContributesAndroidInjector
  abstract fun contributeStepDetailFragment(): StepDetailFragment
}