package com.example.wsw.bakingapp.di

import android.app.Application
import android.arch.persistence.room.Room
import com.example.wsw.bakingapp.api.BakingApi
import com.example.wsw.bakingapp.api.LiveDataCallAdapterFactory
import com.example.wsw.bakingapp.api.RecipeListDeserializer
import com.example.wsw.bakingapp.api.RecipeListResponse
import com.example.wsw.bakingapp.data.BakingDatabase
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by wsw on 17-8-22.
 */
@Module
@Singleton
class AppModule {
  @Singleton
  @Provides
  fun provideBakingApi(): BakingApi {
    val gsonBuilder = GsonBuilder()
    gsonBuilder.registerTypeAdapter(RecipeListResponse::class.java, RecipeListDeserializer())
    return Retrofit.Builder()
        .baseUrl("http://go.udacity.com/")
        .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .build()
        .create(BakingApi::class.java)
  }

  @Singleton
  @Provides
  fun provideDb(app: Application): BakingDatabase = Room.databaseBuilder(app,
      BakingDatabase::class.java,
      "baking.db").build()
}