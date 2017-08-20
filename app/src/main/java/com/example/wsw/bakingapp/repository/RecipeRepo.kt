package com.example.wsw.bakingapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.wsw.bakingapp.data.entity.Ingredient
import com.example.wsw.bakingapp.data.entity.Recipe
import com.example.wsw.bakingapp.data.entity.Step

/**
 * Created by wsw on 17-8-17.
 *
 * Repository for Recipe
 */
class RecipeRepo {
  fun loadRecipeList(): LiveData<Resource<List<Recipe>>> {
    return MutableLiveData<Resource<List<Recipe>>>()
  }

  fun loadRecipe(): LiveData<Resource<Recipe>> {
    return MutableLiveData<Resource<Recipe>>()
  }

  fun loadIngredientList(): LiveData<Resource<List<Ingredient>>> {
    return MutableLiveData<Resource<List<Ingredient>>>()
  }

  fun loadStepList(): LiveData<Resource<List<Step>>> {
    return MutableLiveData<Resource<List<Step>>>()
  }

  fun loadStep(stepId: Int): LiveData<Resource<Step>> {
    return MutableLiveData<Resource<Step>>()
  }
}