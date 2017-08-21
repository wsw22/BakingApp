package com.example.wsw.bakingapp.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.wsw.bakingapp.data.entity.Step

/**
 * Created by wsw on 17-8-21.
 *
 * dao for step
 */
interface StepDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertStepList(stepList: List<Step>)

  @Query("SELECT FROM ${Step.TABLE_NAME} WHERE ${Step.RECIPE_ID} = :recipeId")
  fun loadStepListByRecipeId(recipeId: Int): LiveData<List<Step>>

  @Query("SELECT FROM ${Step.TABLE_NAME} WHERE ${Step.ID} = stepID")
  fun loadStepByStepId(stepId: Int): LiveData<Step>
}