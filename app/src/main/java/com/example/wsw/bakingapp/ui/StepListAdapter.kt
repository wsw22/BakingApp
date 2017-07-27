package com.example.wsw.bakingapp.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.wsw.bakingapp.R
import com.example.wsw.bakingapp.inflate
import com.example.wsw.bakingapp.ui.StepListAdapter.StepHolder
import kotlinx.android.synthetic.main.step_list_item.view.step_list_item_name_text

/**
 * Created by wsw on 17-7-27.
 *
 */
class StepListAdapter(var mArray: Array<String>?,
    val mContext: Context, val listener: (String?) -> Unit) : RecyclerView.Adapter<StepHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = StepHolder(
      parent!!.inflate(R.layout.step_list_item))

  override fun getItemCount() = mArray?.size ?: 0

  override fun onBindViewHolder(holder: StepHolder?, position: Int) = holder!!.bind(
      mArray?.get(position))

  inner class StepHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: String?) = with(itemView) {
      step_list_item_name_text.text = item
      setOnClickListener { listener(item) }
    }
  }
}