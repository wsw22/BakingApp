package com.example.wsw.bakingapp.api

import com.example.wsw.bakingapp.data.entity.Ingredient
import com.example.wsw.bakingapp.data.entity.Recipe
import com.example.wsw.bakingapp.data.entity.Step
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import timber.log.Timber
import java.lang.reflect.Type

/**
 * Created by wsw on 17-8-21.
 */
class RecipeListDeserializer : JsonDeserializer<RecipeListResponse> {
  override fun deserialize(json: JsonElement, typeOfT: Type,
      context: JsonDeserializationContext): RecipeListResponse {
    val recipe: Recipe
    val ingredientList: MutableList<Ingredient> = mutableListOf()
    val stepList: MutableList<Step> = mutableListOf()

    val recipeJson = json.asJsonObject
    Timber.e(json.toString())

    val recipeId: Int = recipeJson["id"].asInt
    val recipeName: String = recipeJson["name"].asString
    val servings: Int = recipeJson["servings"].asInt
    val image: String? = recipeJson["image"].asString

    recipe = Recipe(recipeId, recipeName, servings, image)

    val ingredients: JsonArray = recipeJson["ingredients"].asJsonArray
    ingredients.forEach { ingredient: JsonElement ->
      val item = ingredient as JsonObject
      val quantity: Double = item["quantity"].asDouble
      val measure: String = item["measure"].asString
      val name: String = item["ingredient"].asString
      ingredientList.add(Ingredient(null, quantity, measure, name, recipeId))
    }

    val steps: JsonArray = recipeJson["steps"].asJsonArray
    steps.forEach { step: JsonElement ->
      val item = step as JsonObject
      val shortDescription: String = item["shortDescription"].asString
      val description: String = item["description"].asString
      val videoURL: String = item["videoURL"].asString
      val thumbnailURL: String? = item["thumbnailURL"].asString
      val order: Int = item["id"].asInt
      stepList.add(
          Step(null, shortDescription, description, videoURL, thumbnailURL, order, recipeId))
    }

    return RecipeListResponse(recipe, ingredientList, stepList)
  }
}