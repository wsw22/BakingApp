package com.example.wsw.bakingapp

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
