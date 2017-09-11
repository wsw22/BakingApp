package com.example.wsw.bakingapp.ui.step

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.wsw.bakingapp.R
import com.example.wsw.bakingapp.R.layout
import com.example.wsw.bakingapp.viewModel.RecipeDetailViewModel
import dagger.android.AndroidInjection

class StepDetailActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(layout.step_detail_activity)

    if (savedInstanceState == null) {
//      val viewModel = ViewModelProviders.of(this).get(StepDetailViewModel::class.java)
      val viewModel = ViewModelProviders.of(this).get(RecipeDetailViewModel::class.java)
      viewModel.setStepId(intent.getIntExtra(StepDetailFragment.STEP_ID, -1))

      val stepDetailFragment = StepDetailFragment.newInstance()
      supportFragmentManager.beginTransaction().add(R.id.step_detail_fragment_container,
          stepDetailFragment).commit()
    }
  }

}
