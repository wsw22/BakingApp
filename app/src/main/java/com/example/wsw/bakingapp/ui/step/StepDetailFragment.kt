package com.example.wsw.bakingapp.ui.step

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wsw.bakingapp.R
import com.example.wsw.bakingapp.repository.Status.ERROR
import com.example.wsw.bakingapp.repository.Status.LOADING
import com.example.wsw.bakingapp.repository.Status.SUCCESS
import com.example.wsw.bakingapp.setVisible
import com.example.wsw.bakingapp.viewModel.RecipeDetailViewModel
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.loading_data.loading_data_message
import kotlinx.android.synthetic.main.loading_data.loading_data_progress
import kotlinx.android.synthetic.main.step_detail_fragment.step_detail_description_text
import kotlinx.android.synthetic.main.step_detail_fragment.step_detail_player
import kotlinx.android.synthetic.main.step_detail_fragment.step_detail_short_description_text
import kotlinx.android.synthetic.main.step_detail_fragment.step_detail_video_placeholder_image
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

  private var player: SimpleExoPlayer? = null
  private var uri: Uri? = null

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val rootView = inflater!!.inflate(R.layout.step_detail_fragment, container, false)
    val viewModel = ViewModelProviders.of(activity).get(RecipeDetailViewModel::class.java)

    viewModel.step.observe(this, Observer { resource ->
      if (resource?.data == null) {
        loading_data_progress.visibility = View.GONE
        loading_data_message.visibility = View.VISIBLE
        loading_data_message.text = getString(R.string.message_have_no_data)
        return@Observer
      }

      loading_data_progress.setVisible(resource.status == LOADING)
      loading_data_message.setVisible(resource.status == ERROR)

      if (resource.status == SUCCESS) {
        with(resource.data) {
          if (!TextUtils.isEmpty(videoURL)) {
            step_detail_player.visibility = View.VISIBLE
            step_detail_video_placeholder_image.visibility = View.GONE
            uri = Uri.parse(videoURL)
            preparePlayer(uri)
          } else {
            step_detail_player.visibility = View.GONE
            step_detail_video_placeholder_image.visibility = View.VISIBLE
          }
          step_detail_short_description_text?.text = shortDescription
          step_detail_description_text?.text = description
        }
      } else if (resource.status == ERROR) {
        loading_data_message.text = resource.message ?: getString(R.string.message_fail_get_data)
      }
    })

    return rootView
  }

  override fun onResume() {
    super.onResume()
    preparePlayer(uri)
  }

  override fun onPause() {
    super.onPause()
    player?.release()
  }

  override fun getLifecycle() = lifeCycle

  override fun supportFragmentInjector() = fragmentInjector

  private fun preparePlayer(uri: Uri?) {
    if (uri == null) {
      return
    }
    if (player == null) {
      val bandwidthMeter = DefaultBandwidthMeter()
      val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
      val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
      player = ExoPlayerFactory.newSimpleInstance(context, trackSelector)
      step_detail_player.player = player

      val dataSourceFactory = DefaultDataSourceFactory(context,
          Util.getUserAgent(context, resources.getString(R.string.app_name)))
      val extractorsFactory = DefaultExtractorsFactory()
      val videoSource = ExtractorMediaSource(uri, dataSourceFactory,
          extractorsFactory,
          null, null)
      player?.prepare(videoSource)
    }
  }
}
