package com.example.wsw.bakingapp.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.wsw.bakingapp.R.array
import com.example.wsw.bakingapp.R.layout
import kotlinx.android.synthetic.main.recipe_list_activity.*

class RecipeListActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.recipe_list_activity)

    val dummyRecipeList = resources.getStringArray(array.dummy_recipes)
    val recipeAdapter = RecipeListAdapter(dummyRecipeList, this) {
      val intentStartDetailActivity = Intent(this, RecipeDetailActivity::class.java)
      startActivity(intentStartDetailActivity)
    }
    recipe_list_recycler.adapter = recipeAdapter

    val gridLayoutManager = GridLayoutManager(this, 2)
    recipe_list_recycler.layoutManager = gridLayoutManager
  }
}
