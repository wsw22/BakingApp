package com.example.wsw.bakingapp.ui.ingredient

import android.arch.lifecycle.ViewModel
import com.example.wsw.bakingapp.repository.IngredientRepo

/**
 * Created by wsw on 17-8-18.
 *
 * ingredient list view model
 */
class IngredientListViewModel(repo: IngredientRepo) : ViewModel() {
  val ingredientList = repo.loadIngredientList()
}