package com.example.wsw.bakingapp

import android.content.Context
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Created by wsw on 17-7-27.
 *
 * define extension functions
 */

fun ViewGroup.inflate(layoutRes: Int): View {
  return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun View.setVisible(visible: Boolean) {
  if (visible) {
    this.visibility = View.VISIBLE
  } else {
    this.visibility = View.GONE
  }
}

fun Context.checkNetwork(): Boolean {
  val cm: ConnectivityManager = getSystemService(
      Context.CONNECTIVITY_SERVICE) as ConnectivityManager

  val activeNetwork = cm.activeNetworkInfo
  return activeNetwork?.isConnectedOrConnecting ?: false
}
