package com.example.wsw.bakingapp

import android.arch.lifecycle.LiveData

/**
 * Created by wsw on 17-8-20.
 */
class AbsentLiveData<T> : LiveData<T>() {
  init {
    postValue(null)
  }
}