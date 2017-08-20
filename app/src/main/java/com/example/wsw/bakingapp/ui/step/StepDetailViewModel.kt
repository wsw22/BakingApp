package com.example.wsw.bakingapp.ui.step

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.wsw.bakingapp.AbsentLiveData
import com.example.wsw.bakingapp.data.entity.Step
import com.example.wsw.bakingapp.repository.RecipeRepo
import com.example.wsw.bakingapp.repository.Resource

/**
 * Created by wsw on 17-8-19.
 *
 * view model for step detail activity
 */
class StepDetailViewModel(repo: RecipeRepo) : ViewModel() {
  private val stepID = MutableLiveData<Int>()

  val step: LiveData<Resource<Step>>

  init {
    step = Transformations.switchMap(stepID) { newStepId ->
      if (newStepId == null) {
        return@switchMap AbsentLiveData<Resource<Step>>()
      }
      return@switchMap repo.loadStep(newStepId)
    }
  }

  fun setStepId(newId: Int) {
    if (newId < 0) {
      return
    }
    if (newId == stepID.value) {
      return
    }
    stepID.value = newId
  }
}