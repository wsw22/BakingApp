package com.example.wsw.bakingapp.ui

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.wsw.bakingapp.GlideRequests
import com.example.wsw.bakingapp.R
import com.example.wsw.bakingapp.data.entity.Recipe
import com.example.wsw.bakingapp.inflate
import com.example.wsw.bakingapp.ui.RecipeListAdapter.RecipeHolder
import kotlinx.android.synthetic.main.recipe_list_item.view.*

/**
 * Created by wsw on 17-7-25.
 *
 * adapter for recipe list (RecyclerView)
 */
class RecipeListAdapter(
    private var data: List<Recipe>?,
    private val glideRequests: GlideRequests,
    private val listener: (Recipe?) -> Unit) :
    RecyclerView.Adapter<RecipeHolder>() {
  override fun onBindViewHolder(holder: RecipeHolder?, position: Int) = holder!!.bind(
      data?.get(position))

  override fun getItemCount() = data?.size ?: 0

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = RecipeHolder(
      parent!!.inflate(R.layout.recipe_list_item))

  fun setData(newData: List<Recipe>?) {
    when {
      data == null -> {
        if (newData == null) {
          return
        }
        data = newData
        notifyDataSetChanged()
      }
      newData == null -> {
        val oldSize: Int = data?.size ?: 0
        data = null
        notifyItemRangeChanged(0, oldSize)
      }
      else -> {
        val oldData: List<Recipe> = data!!
        data = newData
        val diffResult = DiffUtil.calculateDiff(RecipeDiffCallBcak(newData, oldData))
        diffResult.dispatchUpdatesTo(this)
      }
    }
  }

  inner class RecipeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Recipe?) = with(itemView) {
      recipe_list_item_name_text.text = item?.name

      glideRequests.load(item?.imageUri)
          .placeholder(R.drawable.baking)
          .into(recipe_list_item_thumbnail_image)

      setOnClickListener { listener(item) }
    }
  }

  inner class RecipeDiffCallBcak(private val newData: List<Recipe>,
      private val oldData: List<Recipe>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldData[oldItemPosition].id == newData[newItemPosition].id

    override fun getOldListSize() = oldData.size

    override fun getNewListSize() = newData.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldData[oldItemPosition] == newData[newItemPosition]
  }
}