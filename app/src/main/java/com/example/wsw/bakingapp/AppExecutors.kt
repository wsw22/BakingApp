package com.example.wsw.bakingapp

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by wsw on 17-8-21.
 *
 * executors for app
 */
class AppExecutors(val diskIO: Executor, val networkIO: Executor, val mainThread: Executor) {
  constructor() : this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3),
      MainThreadExecutor())

  class MainThreadExecutor : Executor {
    private val mainThreadHandler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
      mainThreadHandler.post(command)
    }

  }
}