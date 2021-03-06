package com.example.wsw.bakingapp.ui.ingredient

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.wsw.bakingapp.R
import com.example.wsw.bakingapp.data.entity.Ingredient
import com.example.wsw.bakingapp.inflate
import com.example.wsw.bakingapp.ui.ingredient.IngredientListAdapter.IngredientViewHolder
import kotlinx.android.synthetic.main.ingredient_list_item.view.ingredient_list_item_measure_text
import kotlinx.android.synthetic.main.ingredient_list_item.view.ingredient_list_item_name_text
import kotlinx.android.synthetic.main.ingredient_list_item.view.ingredient_list_item_quantity_text

/**
 * Created by wsw on 17-7-27.
 *
 */
class IngredientListAdapter(private var data: List<Ingredient>?) :
    RecyclerView.Adapter<IngredientViewHolder>() {
  override fun onBindViewHolder(holder: IngredientViewHolder?, position: Int) = holder!!.bind(
      data?.get(position))

  override fun getItemCount() = data?.size ?: 0

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = IngredientViewHolder(
      parent!!.inflate(R.layout.ingredient_list_item))

  fun setData(newData: List<Ingredient>?) {
    when {
      data == null -> {
        if (newData == null) {
          return
        }
        data = newData
        notifyDataSetChanged()
      }
      newData == null -> {
        val oldSize = data!!.size
        data = null
        notifyItemRangeChanged(0, oldSize)
      }
      else -> {
        val oldData = data!!
        data = newData
        val diffResult = DiffUtil.calculateDiff(IngredientDiffUtil(oldData, newData))
        diffResult.dispatchUpdatesTo(this)
      }
    }
  }

  inner class IngredientViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Ingredient?) = with(itemView) {
      ingredient_list_item_quantity_text.text = item?.quantity.toString()
      ingredient_list_item_measure_text.text = item?.measure
      ingredient_list_item_name_text.text = item?.name
    }
  }

  inner class IngredientDiffUtil(private val oldData: List<Ingredient>,
      private val newData: List<Ingredient>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldData[oldItemPosition].id == newData[newItemPosition].id

    override fun getOldListSize() = oldData.size

    override fun getNewListSize() = newData.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldData[oldItemPosition] == newData[newItemPosition]
  }
}