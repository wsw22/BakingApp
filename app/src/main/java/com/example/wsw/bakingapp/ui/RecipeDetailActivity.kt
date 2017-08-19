package com.example.wsw.bakingapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.wsw.bakingapp.R
import kotlinx.android.synthetic.main.recipe_detail_activity.recipe_detail_pager
import kotlinx.android.synthetic.main.recipe_detail_activity.recipe_detail_tabs

class RecipeDetailActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.recipe_detail_activity)

    recipe_detail_pager.adapter = RecipeDetailPagerAdapter(this,
        supportFragmentManager)

    recipe_detail_tabs.setupWithViewPager(recipe_detail_pager)
  }
}
