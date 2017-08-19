package com.example.wsw.bakingapp.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.wsw.bakingapp.data.entity.Recipe
import com.example.wsw.bakingapp.repository.RecipeRepo
import com.example.wsw.bakingapp.repository.Resource

/**
 * Created by wsw on 17-8-17.
 *
 * view model for recipe list
 */
class RecipeListViewModel(repo: RecipeRepo) : ViewModel() {
  val recipeList: LiveData<Resource<List<Recipe>>> = repo.loadRecipeList()
}