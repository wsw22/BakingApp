package com.example.wsw.bakingapp.data

import android.arch.persistence.room.Database
import com.example.wsw.bakingapp.data.dao.IngredientDao
import com.example.wsw.bakingapp.data.dao.RecipeDao
import com.example.wsw.bakingapp.data.dao.StepDao
import com.example.wsw.bakingapp.data.entity.Ingredient
import com.example.wsw.bakingapp.data.entity.Recipe
import com.example.wsw.bakingapp.data.entity.Step

/**
 * Created by wsw on 17-8-16.
 *
 * database for app
 */
@Database(entities = arrayOf(Recipe::class, Ingredient::class, Step::class), version = 1)
abstract class BakingDatabase {
  abstract fun recipeDao(): RecipeDao

  abstract fun ingredientDao(): IngredientDao

  abstract fun stepDao(): StepDao
}