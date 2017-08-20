package com.example.wsw.bakingapp.ui.step

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wsw.bakingapp.R
import com.example.wsw.bakingapp.R.layout
import com.example.wsw.bakingapp.repository.Status.ERROR
import com.example.wsw.bakingapp.repository.Status.LOADING
import com.example.wsw.bakingapp.repository.Status.SUCCESS
import com.example.wsw.bakingapp.setVisible
import com.example.wsw.bakingapp.ui.RecipeDetailViewModel
import kotlinx.android.synthetic.main.loading_data.loading_data_message
import kotlinx.android.synthetic.main.loading_data.loading_data_progress
import kotlinx.android.synthetic.main.step_list_fragment.step_list_recycler
import kotlinx.android.synthetic.main.step_list_fragment.view.step_list_recycler
import java.util.Collections

class StepListFragment : Fragment(), LifecycleRegistryOwner {
  private val lifecycle = LifecycleRegistry(this)

  companion object {
    fun newInstance(): StepListFragment {
      val fragment = StepListFragment()
      return fragment
    }
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater!!.inflate(layout.step_list_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    val viewModel = ViewModelProviders.of(activity).get(RecipeDetailViewModel::class.java)

    val adapter = StepListAdapter(Collections.emptyList()) {
      val intentStartStepDetailActivity = Intent(context, StepDetailActivity::class.java)
      startActivity(intentStartStepDetailActivity)
    }
    view!!.step_list_recycler.adapter = adapter

    val linearLayoutManager = LinearLayoutManager(context)
    view!!.step_list_recycler.layoutManager = linearLayoutManager

    viewModel.stepList.observe(this, Observer { resource ->
      if (resource?.data == null) {
        loading_data_progress.visibility = View.GONE
        step_list_recycler.visibility = View.GONE
        loading_data_message.visibility = View.VISIBLE
        loading_data_message.text = getString(R.string.message_have_no_data)
        return@Observer
      }

      step_list_recycler.setVisible(resource.status == SUCCESS)
      loading_data_progress.setVisible(resource.status == LOADING)
      loading_data_message.setVisible(resource.status == ERROR)

      if (resource.status == SUCCESS) {
        adapter.setData(resource.data)
      } else if (resource.status == ERROR) {
        loading_data_message.text = resource.message ?: getString(
            R.string.message_fail_get_data)
      }
    })
  }

  override fun getLifecycle() = lifecycle
}
