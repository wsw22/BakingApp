package com.example.wsw.bakingapp.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.wsw.bakingapp.AbsentLiveData
import com.example.wsw.bakingapp.data.entity.Ingredient
import com.example.wsw.bakingapp.data.entity.Recipe
import com.example.wsw.bakingapp.data.entity.Step
import com.example.wsw.bakingapp.repository.RecipeRepo
import com.example.wsw.bakingapp.repository.Resource

/**
 * Created by wsw on 17-8-19.
 *
 * view model for recipe detail
 */
class RecipeDetailViewModel(repo: RecipeRepo) : ViewModel() {
  private val id: MutableLiveData<Int> = MutableLiveData()

  val recipe: LiveData<Resource<Recipe>>

  val ingredientList: LiveData<Resource<List<Ingredient>>>

  val stepList: LiveData<Resource<List<Step>>>

  init {
    recipe = Transformations.switchMap(id) { recipeId ->
      if (recipeId == null) {
        return@switchMap AbsentLiveData<Resource<Recipe>>()
      }
      return@switchMap repo.loadRecipe()
    }

    ingredientList = Transformations.switchMap(id) { recipeId ->
      if (recipeId == null) {
        return@switchMap AbsentLiveData<Resource<List<Ingredient>>>()
      }
      return@switchMap repo.loadIngredientList()
    }

    stepList = Transformations.switchMap(id) { recipeID ->
      if (recipeID == null) {
        return@switchMap AbsentLiveData<Resource<List<Step>>>()
      }
      return@switchMap repo.loadStepList()
    }
  }

  fun setRecipeId(newId: Int) {
    if (newId == -1) {
      return
    }
    if (id.value == newId) {
      return
    }
    id.value = newId
  }
}