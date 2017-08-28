package com.example.wsw.bakingapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.example.wsw.bakingapp.AppExecutors
import com.example.wsw.bakingapp.api.BakingApi
import com.example.wsw.bakingapp.api.RecipeListResponse
import com.example.wsw.bakingapp.data.BakingDatabase
import com.example.wsw.bakingapp.data.entity.Ingredient
import com.example.wsw.bakingapp.data.entity.Recipe
import com.example.wsw.bakingapp.data.entity.Step
import timber.log.Timber
import java.util.concurrent.TimeUnit.MINUTES
import javax.inject.Inject

/**
 * Created by wsw on 17-8-17.
 *
 * Repository for Recipe
 */
class RecipeRepo @Inject constructor(
    private val appExecutors: AppExecutors,
    private val db: BakingDatabase,
    private val bakingApi: BakingApi
) {
  private val rateLimit: RateLimit<String> = RateLimit(10, MINUTES)

  fun loadRecipeList(): LiveData<Resource<List<Recipe>>> {
    return object : NetworkBoundResource<List<Recipe>, List<RecipeListResponse>>(appExecutors) {
      override fun saveCallResult(callResult: List<RecipeListResponse>) = writeIntoDatabase(
          callResult)

      override fun shouldFetch(data: List<Recipe>?) =
          data == null || data.isEmpty() || rateLimit.shouldFetch("recipe_list")

      override fun loadFromDb(): LiveData<List<Recipe>> {
        Timber.e("load")
        return db.recipeDao().loadAllRecipes()
      }

      override fun createCall() = bakingApi.getRecipeList()

      override fun onFetchFailed() = rateLimit.reset("recipe_list")
    }.asLiveData()
  }

  fun loadRecipe(recipeId: Int): LiveData<Resource<Recipe>> {
    return object : NetworkBoundResource<Recipe, List<RecipeListResponse>>(appExecutors) {
      override fun saveCallResult(callResult: List<RecipeListResponse>) = writeIntoDatabase(
          callResult)

      override fun shouldFetch(data: Recipe?) = data == null

      override fun loadFromDb() = db.recipeDao().loadRecipeByRecipeId(recipeId)

      override fun createCall() = bakingApi.getRecipeList()

    }.asLiveData()
  }

  fun loadIngredientList(recipeId: Int): LiveData<Resource<List<Ingredient>>> {
    return object : NetworkBoundResource<List<Ingredient>, List<RecipeListResponse>>(appExecutors) {
      override fun saveCallResult(callResult: List<RecipeListResponse>) = writeIntoDatabase(
          callResult)

      override fun shouldFetch(data: List<Ingredient>?) = data == null || data.isEmpty()

      override fun loadFromDb() = db.ingredientDao().loadIngredientByRecipeId(recipeId)

      override fun createCall() = bakingApi.getRecipeList()
    }.asLiveData()
  }

  fun loadStepList(recipeId: Int): LiveData<Resource<List<Step>>> {
    return object : NetworkBoundResource<List<Step>, List<RecipeListResponse>>(appExecutors) {
      override fun saveCallResult(callResult: List<RecipeListResponse>) = writeIntoDatabase(
          callResult)

      override fun shouldFetch(data: List<Step>?) = data == null || data.isEmpty()

      override fun loadFromDb() = db.stepDao().loadStepListByRecipeId(recipeId)

      override fun createCall() = bakingApi.getRecipeList()
    }.asLiveData()
  }

  fun loadStep(stepId: Int): LiveData<Resource<Step>> {
    return object : NetworkBoundResource<Step, List<RecipeListResponse>>(appExecutors) {
      override fun saveCallResult(callResult: List<RecipeListResponse>) = writeIntoDatabase(
          callResult)

      override fun shouldFetch(data: Step?) = data == null

      override fun loadFromDb() = db.stepDao().loadStepByStepId(stepId)

      override fun createCall() = bakingApi.getRecipeList()
    }.asLiveData()
  }

  private fun writeIntoDatabase(callResult: List<RecipeListResponse>) =
      callResult.forEach { (recipe, ingredientList, stepList) ->
        db.recipeDao().insertRecipe(recipe)
        db.ingredientDao().insertIngredientList(ingredientList)
        db.stepDao().insertStepList(stepList)
      }
}