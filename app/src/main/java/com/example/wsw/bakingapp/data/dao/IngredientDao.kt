package com.example.wsw.bakingapp.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.wsw.bakingapp.data.entity.Ingredient

/**
 * Created by wsw on 17-8-21.
 *
 * dao for ingredient
 */
@Dao
interface IngredientDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertIngredientList(ingredientList: List<Ingredient>)

//  @Insert(onConflict = OnConflictStrategy.REPLACE)
//  fun insertIngredient(ingredient: Ingredient)

  @Query("SELECT * FROM ${Ingredient.TABLE_NAME} WHERE ${Ingredient.RECIPE_ID} = :recipeId")
  fun loadIngredientByRecipeId(recipeId: Int): LiveData<List<Ingredient>>
}