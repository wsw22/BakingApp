package com.example.wsw.bakingapp.di

import com.example.wsw.bakingapp.ui.RecipeDetailActivity
import com.example.wsw.bakingapp.ui.ingredient.IngredientListFragment
import com.example.wsw.bakingapp.ui.step.StepDetailActivity
import com.example.wsw.bakingapp.ui.step.StepDetailFragment
import com.example.wsw.bakingapp.ui.step.StepListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope

/**
 * Created by wsw on 17-8-22.
 */
@Module
abstract class RecipeDetailModule {
  @ContributesAndroidInjector
  abstract fun contributeRecipeDetailActivity(): RecipeDetailActivity

  @ContributesAndroidInjector
  abstract fun contributeStepListFragment(): StepListFragment

  @ContributesAndroidInjector
  abstract fun contributeIngredientFragment(): IngredientListFragment

  @ContributesAndroidInjector
  abstract fun contributeStepDetalActivity(): StepDetailActivity

  @ContributesAndroidInjector
  abstract fun contributeStepDetailFragment(): StepDetailFragment
}