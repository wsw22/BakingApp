package com.example.wsw.bakingapp.ui.step

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.wsw.bakingapp.R
import com.example.wsw.bakingapp.data.entity.Step
import com.example.wsw.bakingapp.inflate
import com.example.wsw.bakingapp.ui.step.StepListAdapter.StepHolder
import kotlinx.android.synthetic.main.step_list_item.view.step_list_item_name_text

/**
 * Created by wsw on 17-7-27.
 *
 */
class StepListAdapter(private var data: List<Step>?,
    val listener: (Step?) -> Unit) : RecyclerView.Adapter<StepHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = StepHolder(
      parent!!.inflate(R.layout.step_list_item))

  override fun getItemCount() = data?.size ?: 0

  override fun onBindViewHolder(holder: StepHolder?, position: Int) = holder!!.bind(
      data?.get(position))

  fun setData(newData: List<Step>?) {
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
        val diffResult = DiffUtil.calculateDiff(StepDiffUtilCallBack(oldData, newData))
        diffResult.dispatchUpdatesTo(this)
      }
    }
  }

  inner class StepHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Step?) = with(itemView) {
      step_list_item_name_text.text = item?.shortDescription
      setOnClickListener { listener(item) }
    }
  }

  inner class StepDiffUtilCallBack(private val oldData: List<Step>,
      private val newData: List<Step>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldData[oldItemPosition].id == newData[newItemPosition].id

    override fun getOldListSize() = oldData.size

    override fun getNewListSize() = newData.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldData[oldItemPosition] == newData[newItemPosition]

  }

}