package com.example.wsw.bakingapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.wsw.bakingapp.data.entity.Ingredient

/**
 * Created by wsw on 17-8-17.
 *
 * Repository for Ingredient
 */
class IngredientRepo {
  fun loadIngredientList(): LiveData<Resource<List<Ingredient>>> {
    return MutableLiveData<Resource<List<Ingredient>>>()
  }
}