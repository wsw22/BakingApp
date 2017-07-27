package com.example.wsw.bakingapp.ui

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.wsw.bakingapp.R
import java.lang.IllegalArgumentException

/**
 * Created by wsw on 17-7-27.
 *
 */
class RecipeDetailPagerAdapyer(mContext: Context,
    mFragmentManger: FragmentManager) : FragmentPagerAdapter(
    mFragmentManger) {
  val PAGER_COUNT = 2
  val PAGER_INDEX_INGREDIENT = 0
  val PAGER_INDEX_STEP = 1
  val PAGER_TITLES: Array<String> = mContext.resources.getStringArray(
      R.array.recipe_detail_pager_titles)

  override fun getPageTitle(position: Int) = PAGER_TITLES[position]

  override fun getItem(position: Int): Fragment {
    when (position) {
      PAGER_INDEX_INGREDIENT -> return IngredientListFragment.newInstance()
      PAGER_INDEX_STEP -> return StepListFragment.newInstance()
      else -> throw IllegalArgumentException("invalid pager position for recipe detail pagers")
    }
  }

  override fun getCount() = PAGER_COUNT
}