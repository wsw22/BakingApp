package com.example.wsw.bakingapp.repository

import com.example.wsw.bakingapp.repository.Status.ERROR
import com.example.wsw.bakingapp.repository.Status.LOADING
import com.example.wsw.bakingapp.repository.Status.SUCCESS

/**
 * Created by wsw on 17-8-17.
 *
 * resource = data + status
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
  companion object {
    fun <T> success(data: T?) = Resource(SUCCESS, data, null)

    fun <T> error(data: T?, msg: String) = Resource(ERROR, data, msg)

    fun <T> loading(data: T?) = Resource(LOADING, data, null)
  }
}

enum class Status {
  SUCCESS,
  ERROR,
  LOADING
}