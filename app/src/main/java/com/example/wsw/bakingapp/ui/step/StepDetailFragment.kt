package com.example.wsw.bakingapp.ui.step

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wsw.bakingapp.R
import com.example.wsw.bakingapp.repository.Status.ERROR
import com.example.wsw.bakingapp.repository.Status.LOADING
import com.example.wsw.bakingapp.repository.Status.SUCCESS
import com.example.wsw.bakingapp.setVisible
import com.example.wsw.bakingapp.viewModel.StepDetailViewModel
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.loading_data.loading_data_message
import kotlinx.android.synthetic.main.loading_data.loading_data_progress
import kotlinx.android.synthetic.main.step_detail_fragment.step_detail
import kotlinx.android.synthetic.main.step_detail_fragment.step_detail_description_text
import kotlinx.android.synthetic.main.step_detail_fragment.step_detail_short_description_text
import javax.inject.Inject

class StepDetailFragment : Fragment(), LifecycleRegistryOwner, HasSupportFragmentInjector {
  companion object {
    const val STEP_ID = "step_id"

    fun newInstance(): StepDetailFragment {
      return StepDetailFragment()
    }
  }

  private val lifeCycle = LifecycleRegistry(this)

  @Inject
  lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val rootView = inflater!!.inflate(R.layout.step_detail_fragment, container, false)
    val viewModel = ViewModelProviders.of(activity).get(
        StepDetailViewModel::class.java)
    // todo handle tablet
//    val viewModel = ViewModelProviders.of(activity).get(RecipeDetailActivity::class.java)

    viewModel.step.observe(this, Observer { resource ->
      if (resource?.data == null) {
        step_detail.visibility = View.GONE
        loading_data_progress.visibility = View.GONE
        loading_data_message.visibility = View.VISIBLE
        loading_data_message.text = getString(R.string.message_have_no_data)
        return@Observer
      }

      step_detail.setVisible(resource.status == SUCCESS)
      loading_data_progress.setVisible(resource.status == LOADING)
      loading_data_message.setVisible(resource.status == ERROR)

      if (resource.status == SUCCESS) {
        with(resource.data) {
          // todo display video
          step_detail_short_description_text.text = shortDescription
          step_detail_description_text.text = description
        }
      } else if (resource.status == ERROR) {
        loading_data_message.text = resource.message ?: getString(R.string.message_fail_get_data)
      }
    })

    return rootView
  }

  override fun getLifecycle() = lifeCycle

  override fun supportFragmentInjector() = fragmentInjector
}
