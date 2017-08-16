package com.example.wsw.bakingapp.data

/**
 * Created by wsw on 17-7-28.
 */
data class Recipe(val id: Int, val name: String, val ingredients: List<Ingredient>,
    val steps: List<Step>, val servings: Int, val image: String) {
}