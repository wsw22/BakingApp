package com.example.wsw.bakingapp.ui.ingredient

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wsw.bakingapp.R
import com.example.wsw.bakingapp.repository.Status.ERROR
import com.example.wsw.bakingapp.repository.Status.LOADING
import com.example.wsw.bakingapp.repository.Status.SUCCESS
import com.example.wsw.bakingapp.setVisible
import kotlinx.android.synthetic.main.ingredient_list_fragment.ingredient_list_recycler
import kotlinx.android.synthetic.main.ingredient_list_fragment.view.ingredient_list_recycler
import kotlinx.android.synthetic.main.loading_data.loading_data_message
import kotlinx.android.synthetic.main.loading_data.loading_data_progress
import java.util.Collections

class IngredientListFragment : Fragment(), LifecycleRegistryOwner {
  companion object {
    fun newInstance(): IngredientListFragment {
      val fragment = IngredientListFragment()
      return fragment
    }
  }

  private val lifecycle = LifecycleRegistry(this)

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val itemView = inflater!!.inflate(R.layout.ingredient_list_fragment, container, false)
    val viewModel = ViewModelProviders.of(this).get(
        IngredientListViewModel::class.java)

    val adapter = IngredientListAdapter(Collections.emptyList())
    itemView.ingredient_list_recycler.adapter = adapter

    val linearLayoutManger = LinearLayoutManager(context)
    itemView.ingredient_list_recycler.layoutManager = linearLayoutManger

    viewModel.ingredientList.observe(this, Observer { resource ->
      if (resource?.data == null) {
        loading_data_progress.visibility = View.GONE
        ingredient_list_recycler.visibility = View.GONE
        loading_data_message.visibility = View.VISIBLE
        loading_data_message.text = getString(R.string.message_have_no_data)
        return@Observer
      }

      ingredient_list_recycler.setVisible(resource.status == SUCCESS)
      loading_data_progress.setVisible(resource.status == LOADING)
      loading_data_message.setVisible(resource.status == ERROR)

      if (resource.status == SUCCESS) {
        adapter.setData(resource.data)
      } else if (resource.status == ERROR) {
        loading_data_message.text = resource.message ?: getString(
            R.string.message_fail_get_data)
      }
    })

    return itemView
  }

  override fun getLifecycle() = lifecycle
}
