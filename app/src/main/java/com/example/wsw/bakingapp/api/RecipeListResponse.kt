package com.example.wsw.bakingapp.api

import com.example.wsw.bakingapp.data.entity.Ingredient
import com.example.wsw.bakingapp.data.entity.Recipe
import com.example.wsw.bakingapp.data.entity.Step

/**
 * Created by wsw on 17-8-21.
 */
data class RecipeListResponse(
    var recipe: Recipe,
    var ingredientList: List<Ingredient>,
    var stepList: List<Step>
)