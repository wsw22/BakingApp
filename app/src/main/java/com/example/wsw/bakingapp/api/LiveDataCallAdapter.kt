package com.example.wsw.bakingapp.api

import android.arch.lifecycle.LiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.lang.reflect.Type
import java.sql.Time
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by wsw on 17-8-22.
 */
class LiveDataCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, LiveData<ApiResponse<R>>> {
  override fun responseType() = responseType

  override fun adapt(call: Call<R>): LiveData<ApiResponse<R>> {
    return object : LiveData<ApiResponse<R>>() {
      val started = AtomicBoolean(false)

      override fun onActive() {
        super.onActive()
        if (started.compareAndSet(false, true)) {
          call.enqueue(object : Callback<R> {
            override fun onFailure(call: Call<R>, t: Throwable) {
              Timber.e("fail")
              Timber.e(t.message)
              postValue(ApiResponse(t))
            }

            override fun onResponse(call: Call<R>, response: Response<R>) {
              Timber.e("success")
              postValue(ApiResponse(response))
            }
          })
        }
      }
    }
  }
}