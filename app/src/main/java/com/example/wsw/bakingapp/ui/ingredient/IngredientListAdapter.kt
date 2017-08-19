package com.example.wsw.bakingapp.ui.ingredient

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.wsw.bakingapp.R
import com.example.wsw.bakingapp.data.entity.Ingredient
import com.example.wsw.bakingapp.inflate
import com.example.wsw.bakingapp.ui.ingredient.IngredientListAdapter.IngredientViewHolder
import kotlinx.android.synthetic.main.ingredient_list_item.view.ingredient_list_item_name_text

/**
 * Created by wsw on 17-7-27.
 *
 */
class IngredientListAdapter(
    private var data: List<Ingredient>?) : RecyclerView.Adapter<IngredientViewHolder>() {
  override fun onBindViewHolder(holder: IngredientViewHolder?, position: Int) = holder!!.bind(
      data?.get(position))

  override fun getItemCount() = data?.size ?: 0

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = IngredientViewHolder(
      parent!!.inflate(R.layout.ingredient_list_item))

  inner class IngredientViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Ingredient?) = with(itemView) {
      ingredient_list_item_name_text.text = item?.name
    }
  }

  fun setData(ingredientList: List<Ingredient>) {
    // todo set data
  }
}