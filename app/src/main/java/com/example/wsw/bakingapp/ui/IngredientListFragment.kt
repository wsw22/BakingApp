package com.example.wsw.bakingapp.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wsw.bakingapp.R
import com.example.wsw.bakingapp.R.layout
import kotlinx.android.synthetic.main.ingredient_list_fragment.view.ingredient_list_recycler

class IngredientListFragment : Fragment() {

  companion object {
    fun newInstance(): IngredientListFragment {
      val fragment = IngredientListFragment()
      return fragment
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val itemView = inflater!!.inflate(layout.ingredient_list_fragment, container, false)

    val ingredientList = itemView.ingredient_list_recycler
    val dummyIngredientList = resources.getStringArray(R.array.dummy_ingredients)
    val ingredientListAdapter = IngredientListAdapter(dummyIngredientList, context)
    ingredientList.adapter = ingredientListAdapter
    val linearLayoutManger = LinearLayoutManager(context)
    ingredientList.layoutManager = linearLayoutManger

    return itemView
  }
}
