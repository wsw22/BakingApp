package com.example.wsw.bakingapp.ui.step

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.wsw.bakingapp.data.entity.Step
import com.example.wsw.bakingapp.repository.Resource
import com.example.wsw.bakingapp.repository.StepRepo

/**
 * Created by wsw on 17-8-19.
 */
class StepListViewModel(private val repo: StepRepo):ViewModel() {
  fun loadStepList():LiveData<Resource<List<Step>>> = repo.loadStepList()
}