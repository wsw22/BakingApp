package com.example.wsw.bakingapp.di

import com.example.wsw.bakingapp.viewModel.BakingViewModelFactory
import com.example.wsw.bakingapp.viewModel.RecipeDetailViewModel
import com.example.wsw.bakingapp.viewModel.RecipeListViewModel
import com.example.wsw.bakingapp.viewModel.StepDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by wsw on 17-8-23.
 */
@Module
abstract class ViewModelModule {
  @Binds
  @IntoMap
  @ViewModelKey(RecipeListViewModel::class)
  abstract fun bindRecipeListViewModel(recipeListViewModel: RecipeListViewModel)

  @Binds
  @IntoMap
  @ViewModelKey(RecipeDetailViewModel::class)
  abstract fun bindRecipeDetailViewModel(recipeDetailViewModel: RecipeDetailViewModel)

  @Binds
  @IntoMap
  @ViewModelKey(StepDetailViewModel::class)
  abstract fun bindStepDetailViewModel(stepDetailViewModel: StepDetailViewModel)

  @Binds
  abstract fun bindViewModelFactory(bakingViewModelFactory: BakingViewModelFactory)
}