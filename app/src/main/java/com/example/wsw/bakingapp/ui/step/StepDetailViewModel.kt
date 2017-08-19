package com.example.wsw.bakingapp.ui.step

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.wsw.bakingapp.data.entity.Step
import com.example.wsw.bakingapp.repository.Resource

/**
 * Created by wsw on 17-8-19.
 */
class StepDetailViewModel : ViewModel() {
  fun loadStep(): LiveData<Resource<Step>> {
    return MutableLiveData<Resource<Step>>()
  }
}