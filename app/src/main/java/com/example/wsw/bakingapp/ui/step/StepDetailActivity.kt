package com.example.wsw.bakingapp.ui.step

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.wsw.bakingapp.R
import com.example.wsw.bakingapp.R.layout
import com.example.wsw.bakingapp.ui.step.StepDetailFragment.Companion

class StepDetailActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.step_detail_activity)

    if (savedInstanceState == null) {
      val stepDetailFragment = StepDetailFragment.newInstance()
      supportFragmentManager.beginTransaction().add(R.id.step_detail_fragment_container,
          stepDetailFragment).commit()
    }
  }
}
