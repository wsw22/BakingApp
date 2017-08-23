package com.example.wsw.bakingapp.di

import dagger.Component

/**
 * Created by wsw on 17-8-23.
 */
@RecipeDetailScope
@Component(modules = arrayOf(RecipeDetailModule::class))
class RecipeDetailComponent {
}