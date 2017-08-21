package com.example.wsw.bakingapp.api

import retrofit2.Response
import timber.log.Timber
import java.io.IOException

/**
 * Created by wsw on 17-8-21.
 *
 * wrapper for api response
 */
class ApiResponse<T> {
  private val code: Int
  val body: T?
  val errorMessage: String?

  constructor(error: Throwable) {
    code = 500
    body = null
    errorMessage = error.message
  }

  constructor(response: Response<T>) {
    code = response.code()

    if (response.isSuccessful) {
      body = response.body()
      errorMessage = null
    } else {
      var message: String? = null

      if (response.errorBody() != null) {
        try {
          message = response.errorBody()!!.string()
        } catch (error: IOException) {
          Timber.e(error, "error while parsing response")
        }
      }

      if (message == null || message.trim().isEmpty()) {
        message = response.message()
      }
      errorMessage = message
      body = null
    }
  }

  fun isSuccessful() = code in 200..299
}