package com.example.wsw.bakingapp.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import com.example.wsw.bakingapp.R
import com.example.wsw.bakingapp.inflate
import com.example.wsw.bakingapp.ui.IngredientListAdapter.IngredientViewHolder

import kotlinx.android.synthetic.main.ingredient_list_item.view.*

/**
 * Created by wsw on 17-7-27.
 *
 */
class IngredientListAdapter(var mArray: Array<String>?,
    val mContext: Context) : RecyclerView.Adapter<IngredientViewHolder>() {
  override fun onBindViewHolder(holder: IngredientViewHolder?, position: Int) = holder!!.bind(
      mArray?.get(position))

  override fun getItemCount() = mArray?.size ?: 0

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = IngredientViewHolder(
      parent!!.inflate(R.layout.ingredient_list_item))

  inner class IngredientViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: String?) = with(itemView) {
      ingredient_list_item_name_text.text = item
    }
  }
}