package com.example.wsw.bakingapp.ui

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.wsw.bakingapp.R
import com.example.wsw.bakingapp.data.entity.Step
import com.example.wsw.bakingapp.repository.Resource
import com.example.wsw.bakingapp.ui.step.StepDetailFragment
import com.example.wsw.bakingapp.viewModel.RecipeDetailViewModel
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.recipe_detail_activity.recipe_detail_pager
import kotlinx.android.synthetic.main.recipe_detail_activity.recipe_detail_tabs
import timber.log.Timber
import javax.inject.Inject

class RecipeDetailActivity : AppCompatActivity(), LifecycleRegistryOwner, HasSupportFragmentInjector {
  companion object {
    const val RECIPE_ID = "recipe_id"
  }

  private val lifecycle = LifecycleRegistry(this)

  @Inject
  lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.recipe_detail_activity)

    val viewModel = ViewModelProviders.of(this).get(RecipeDetailViewModel::class.java)

    if (intent != null && intent.hasExtra(RECIPE_ID)) {
      viewModel.setRecipeId(intent.getIntExtra(RECIPE_ID, -1))
    }

    recipe_detail_pager.adapter = RecipeDetailPagerAdapter(this,
        supportFragmentManager)

    recipe_detail_tabs.setupWithViewPager(recipe_detail_pager)

    val isTwoPane = resources.getBoolean(R.bool.is_two_pane)
    if (isTwoPane) {
      if (savedInstanceState == null) {
        val stepDetailFragment = StepDetailFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.step_detail_fragment_container,
            stepDetailFragment).commit()
      }
    }
  }

  override fun getLifecycle() = lifecycle

  override fun supportFragmentInjector() = fragmentInjector
}
