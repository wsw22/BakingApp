package com.example.wsw.bakingapp.repository

import android.arch.lifecycle.LiveData
import com.example.wsw.bakingapp.data.entity.Step

/**
 * Created by wsw on 17-8-17.
 *
 * Repository for Step
 */
class StepRepo {
  fun loadStepList(): LiveData<Resource<List<Step>>> {

  }
}