package com.example.wsw.bakingapp.repository

import android.arch.lifecycle.LiveData
import com.example.wsw.bakingapp.data.entity.Ingredient

/**
 * Created by wsw on 17-8-17.
 *
 * Repository for Ingredient
 */
class IngredientRepo {
  fun loadIngredientList: LiveData<Resource<List<Ingredient>>> {

  }
}