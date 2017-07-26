package com.example.wsw.bakingapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.wsw.bakingapp.ui.RecipeAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val dummyRecipeList = resources.getStringArray(R.array.dummy_recipes)
    val recipeAdapter = RecipeAdapter(dummyRecipeList, this)
    recycler_recipe_list.adapter = recipeAdapter

    val linearLayoutManager = LinearLayoutManager(this)
    recycler_recipe_list.layoutManager = linearLayoutManager
  }
}
