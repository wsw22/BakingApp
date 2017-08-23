package com.example.wsw.bakingapp.di

import dagger.Component

/**
 * Created by wsw on 17-8-23.
 */

@RecipeListScope
@Component(modules = arrayOf(RecipeListModule::class))
class RecipeListComponent {
}