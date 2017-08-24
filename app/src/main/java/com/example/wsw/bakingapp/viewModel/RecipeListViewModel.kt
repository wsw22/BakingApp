package com.example.wsw.bakingapp.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.util.Log
import com.example.wsw.bakingapp.MyApp
import com.example.wsw.bakingapp.data.entity.Recipe
import com.example.wsw.bakingapp.repository.RecipeRepo
import com.example.wsw.bakingapp.repository.Resource
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by wsw on 17-8-17.
 *
 * view model for recipe list
 */
class RecipeListViewModel(myApp: Application) : AndroidViewModel(myApp) {
  @Inject
  lateinit var repo: RecipeRepo

  val recipeList: LiveData<Resource<List<Recipe>>>

  init {
    if (myApp is MyApp) {
      myApp.appComponent.inject(this)
    } else {
      throw IllegalArgumentException("application must be MyApp!")
    }
    recipeList = repo.loadRecipeList()
  }
}