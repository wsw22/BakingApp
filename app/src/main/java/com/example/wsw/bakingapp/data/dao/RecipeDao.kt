package com.example.wsw.bakingapp.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.wsw.bakingapp.data.entity.Recipe

/**
 * Created by wsw on 17-8-21.
 *
 * dao for recipe
 */
@Dao
interface RecipeDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertRecipe(recipe: Recipe)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertRecipeList(recipeList: List<Recipe>)

  @Query("SELECT * FROM ${Recipe.TABLE_NAME} WHERE ${Recipe.ID} = :recipeId")
  fun loadRecipeByRecipeId(recipeId: Int): LiveData<Recipe>

  @Query("SELECT * FROM ${Recipe.TABLE_NAME}")
  fun loadAllRecipes(): LiveData<List<Recipe>>
}