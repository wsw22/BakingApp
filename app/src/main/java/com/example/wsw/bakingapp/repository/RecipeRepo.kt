package com.example.wsw.bakingapp.repository

import android.arch.lifecycle.LiveData
import com.example.wsw.bakingapp.data.entity.Recipe

/**
 * Created by wsw on 17-8-17.
 *
 * Repository for Recipe
 */
class RecipeRepo {
  fun loadRecipeList(): LiveData<Resource<List<Recipe>>> {
    // todo load recipe list
  }
}