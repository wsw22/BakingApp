package com.example.wsw.bakingapp.api

import android.arch.lifecycle.LiveData
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by wsw on 17-8-22.
 */
class LiveDataCallAdapterFactory : CallAdapter.Factory() {
  override fun get(returnType: Type, annotations: Array<out Annotation>?,
      retrofit: Retrofit): CallAdapter<*, *>? {
//    LiveData<ApiResponse<>>>

//    LiveData
    if (getRawType(returnType) != LiveData::class) {
      return null
    }

//    ApiResponse<>
    val observableType = getParameterUpperBound(0, returnType as ParameterizedType)

//    ApiResponse
    val rawObservableType = getRawType(observableType)

    if (rawObservableType != ApiResponse::class) {
      throw IllegalArgumentException("type must be a resource")
    }

    if (observableType !is ParameterizedType) {
      throw IllegalArgumentException("resource must be parameterized")
    }

    val bodyType = getParameterUpperBound(0, observableType)

    return LiveDataCallAdapter<Any>(bodyType)
  }
}