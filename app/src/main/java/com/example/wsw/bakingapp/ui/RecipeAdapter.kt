package com.example.wsw.bakingapp.ui

import android.content.Context
import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.wsw.bakingapp.R
import kotlinx.android.synthetic.main.item_recipe.view.item_recipe_name

/**
 * Created by wsw on 17-7-25.
 *
 * adapter for recipe list (RecyclerView)
 */
class RecipeAdapter(private var mArray: Array<String>?,
    private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    val recipeName = mArray?.get(position)
    if (holder is RecipeHolder) {
      holder.recipeNameText.text = recipeName
    }
  }

  override fun getItemCount() = mArray?.size ?: 0

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
    val itemView = LayoutInflater.from(mContext).inflate(R.layout.item_recipe, parent, false)
    return RecipeHolder(itemView)
  }

  inner class RecipeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val recipeNameText: TextView = itemView.item_recipe_name
  }
}