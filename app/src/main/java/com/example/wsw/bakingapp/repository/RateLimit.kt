package com.example.wsw.bakingapp.repository

import android.os.SystemClock
import java.util.concurrent.TimeUnit

/**
 * Created by wsw on 17-8-22.
 */
class RateLimit<in KEY>(timeout: Long, timeUnit: TimeUnit) {
  private val timestamps = android.support.v4.util.ArrayMap<KEY, Long>()
  private val timeout = timeUnit.toMillis(timeout)

  fun shouldFetch(key: KEY): Boolean {
    val lastFetched = timestamps[key]
    val now = now()

    if (lastFetched == null) {
      timestamps.put(key, now)
      return true
    }

    if (now - lastFetched > timeout) {
      timestamps.put(key, now)
      return true
    }

    return false
  }

  fun reset(key: KEY) {
    timestamps.remove(key)
  }

  private fun now() = SystemClock.uptimeMillis()
}