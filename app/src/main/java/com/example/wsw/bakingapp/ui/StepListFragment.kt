package com.example.wsw.bakingapp.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wsw.bakingapp.R
import com.example.wsw.bakingapp.R.layout
import kotlinx.android.synthetic.main.step_list_fragment.view.step_list_recycler

class StepListFragment : Fragment() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val itemView = inflater!!.inflate(R.layout.step_list_fragment, container, false)

    val stepList = itemView.step_list_recycler
    val dummyStepList = resources.getStringArray(R.array.dummy_steps)
    val stepListAdapter = StepListAdapter(dummyStepList, context) {
      val intentStartStepDetailActivity = Intent(context, StepDetailActivity::class.java)
      Log.e("AAA","AAAA")
      startActivity(intentStartStepDetailActivity)
    }
    stepList.adapter = stepListAdapter
    val linearLayoutManager = LinearLayoutManager(context)
    stepList.layoutManager = linearLayoutManager

    return itemView
  }

  companion object {
    fun newInstance(): StepListFragment {
      val fragment = StepListFragment()
      return fragment
    }
  }
}
