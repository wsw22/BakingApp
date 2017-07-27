package com.example.wsw.bakingapp.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.wsw.bakingapp.R
import com.example.wsw.bakingapp.inflate
import com.example.wsw.bakingapp.ui.RecipeListAdapter.RecipeHolder
import kotlinx.android.synthetic.main.recipe_list_item.view.*

/**
 * Created by wsw on 17-7-25.
 *
 * adapter for recipe list (RecyclerView)
 */
class RecipeListAdapter(private var mArray: Array<String>?,
    private val mContext: Context,
    val listener: (String?) -> Unit) : RecyclerView.Adapter<RecipeHolder>() {
  override fun onBindViewHolder(holder: RecipeHolder?, position: Int) = holder!!.bind(
      mArray?.get(position))

  override fun getItemCount() = mArray?.size ?: 0

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = RecipeHolder(
      parent!!.inflate(R.layout.recipe_list_item))

  inner class RecipeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: String?) = with(itemView) {
      recipe_list_item_name_text.text = item
      setOnClickListener { listener(item) }
    }
  }
}