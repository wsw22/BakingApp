package com.example.wsw.bakingapp.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by wsw on 17-8-23.
 */
@Singleton
class BakingViewModelFactory @Inject constructor(
    private val viewModelMap: Map<Class<out ViewModel>, ViewModel>) :
    ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    var viewModel = viewModelMap[modelClass]

    if (viewModel == null) {
      for ((key, value) in viewModelMap) {
        if (modelClass.isAssignableFrom(key)) {
          viewModel = value
          break
        }
      }
    }

    if (viewModel == null) {
      throw IllegalArgumentException("unknown model class " + modelClass)
    }

    return viewModel as T
  }
}