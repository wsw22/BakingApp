package com.example.wsw.bakingapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.example.wsw.bakingapp.AppExecutors
import com.example.wsw.bakingapp.api.ApiResponse
import timber.log.Timber

/**
 * Created by wsw on 17-8-21.
 *
 * from android developer
 */
abstract class NetworkBoundResource<ResultType, RequestType> @MainThread constructor(
    private val appExecutors: AppExecutors) {
  private val result = MediatorLiveData<Resource<ResultType>>()

  init {
    result.value = Resource.loading(null)

    val dbSource = loadFromDb()

    result.addSource(dbSource) { data ->
      result.removeSource(dbSource)
      if (shouldFetch(data)) {
        Timber.e("should fetch")
        fetchFromNetwork(dbSource)
      } else {
        Timber.e("do not need fetch")
        result.addSource(dbSource) { newData ->
          result.value = Resource.success(newData)
        }
      }
    }
  }

  private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
    val apiResponse = createCall()

    result.addSource(dbSource) { newData ->
      result.value = Resource.loading(newData)
    }

    result.addSource(apiResponse) { response ->
      result.removeSource(apiResponse)
      result.removeSource(dbSource)

      if (response!!.isSuccessful()) {
        appExecutors.diskIO.execute {
          saveCallResult(processResponse(response))
          appExecutors.mainThread.execute {
            result.addSource(loadFromDb()) { newData ->
              result.value = Resource.success(newData)
            }
          }
        }
      } else {
        onFetchFailed()
        result.addSource(dbSource) { newData ->
          result.value = Resource.error(newData, response.errorMessage!!)
        }
      }
    }
  }

  fun asLiveData(): LiveData<Resource<ResultType>> = result

  protected open fun onFetchFailed() {}

  @WorkerThread
  protected open fun processResponse(
      response: ApiResponse<RequestType>): RequestType = response.body!!

  @WorkerThread
  protected abstract fun saveCallResult(callResult: RequestType)

  @MainThread
  protected abstract fun shouldFetch(data: ResultType?): Boolean

  @MainThread
  protected abstract fun loadFromDb(): LiveData<ResultType>

  @MainThread
  protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}