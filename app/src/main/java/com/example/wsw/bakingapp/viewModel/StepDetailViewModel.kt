package com.example.wsw.bakingapp.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.example.wsw.bakingapp.AbsentLiveData
import com.example.wsw.bakingapp.MyApp
import com.example.wsw.bakingapp.data.entity.Step
import com.example.wsw.bakingapp.repository.RecipeRepo
import com.example.wsw.bakingapp.repository.Resource
import javax.inject.Inject

/**
 * Created by wsw on 17-8-19.
 *
 * view model for step detail activity
 */
class StepDetailViewModel(myApp: Application) : AndroidViewModel(myApp) {
  @Inject
  lateinit var repo: RecipeRepo

  private val stepID = MutableLiveData<Int>()

  val step: LiveData<Resource<Step>>

  init {
    if (myApp is MyApp) {
      myApp.appComponent.inject(this)
    } else {
      throw IllegalArgumentException("application must be MyApp")
    }

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