package com.example.wsw.bakingapp.ui.step

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

  inner class StepHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Step?) = with(itemView) {
      step_list_item_name_text.text = item?.shortDescription
      setOnClickListener { listener(item) }
    }
  }

  fun setData(newData: List<Step>) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}