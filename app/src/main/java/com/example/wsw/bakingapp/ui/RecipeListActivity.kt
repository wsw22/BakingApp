package com.example.wsw.bakingapp.ui

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.wsw.bakingapp.GlideApp
import com.example.wsw.bakingapp.R
import com.example.wsw.bakingapp.R.layout
import com.example.wsw.bakingapp.checkNetwork
import com.example.wsw.bakingapp.repository.Status.ERROR
import com.example.wsw.bakingapp.repository.Status.LOADING
import com.example.wsw.bakingapp.repository.Status.SUCCESS
import com.example.wsw.bakingapp.setVisible
import com.example.wsw.bakingapp.viewModel.RecipeListViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.loading_data.loading_data_message
import kotlinx.android.synthetic.main.loading_data.loading_data_progress
import kotlinx.android.synthetic.main.recipe_list_activity.recipe_list_recycler
import java.util.Collections

class RecipeListActivity : AppCompatActivity(), LifecycleRegistryOwner {
  private val lifecycleRegistry = LifecycleRegistry(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)

    super.onCreate(savedInstanceState)
    setContentView(layout.recipe_list_activity)

    val viewModel = ViewModelProviders.of(this).get(RecipeListViewModel::class.java)

    val adapter = RecipeListAdapter(Collections.emptyList(), GlideApp.with(this)) {
      val intentStartDetailActivity = Intent(this, RecipeDetailActivity::class.java)
      intentStartDetailActivity.putExtra(RecipeDetailActivity.RECIPE_ID, it!!.id)
      startActivity(intentStartDetailActivity)
    }
    recipe_list_recycler.adapter = adapter

    val spanCount = resources.getInteger(R.integer.recipe_span_count)
    val gridLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, spanCount)
    recipe_list_recycler.layoutManager = gridLayoutManager

    viewModel.recipeList.observe(this, Observer { resource ->
      if (resource?.data == null) {
        recipe_list_recycler.setVisible(false)
        loading_data_progress.setVisible(false)
        loading_data_message.setVisible(true)
        loading_data_message.text = getString(R.string.message_have_no_data)
        return@Observer
      }

      recipe_list_recycler.setVisible(resource.status == SUCCESS)
      loading_data_progress.setVisible(resource.status == LOADING)
      loading_data_message.setVisible(resource.status == ERROR)

      if (resource.status == SUCCESS) {
        adapter.setData(resource.data)
      } else if (!checkNetwork()) {
        loading_data_message.text = getString(R.string.message_no_connection)
      } else if (resource.status == ERROR) {
        loading_data_message.text = resource.message ?: getString(R.string.message_fail_get_data)
      }
    })
  }

  override fun getLifecycle() = lifecycleRegistry

}
