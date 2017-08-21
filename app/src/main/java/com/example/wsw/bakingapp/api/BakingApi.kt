package com.example.wsw.bakingapp.api

import android.arch.lifecycle.LiveData
import retrofit2.http.GET

/**
 * Created by wsw on 17-8-21.
 *
 * http://go.udacity.com/android-baking-app-json
 */
interface BakingApi {
  @GET("android-baking-app-json")
  fun getRecipeList(): LiveData<ApiResponse<List<RecipeListResponse>>>
}