package com.example.wsw.bakingapp.di

import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

/**
 * Created by wsw on 17-8-24.
 */
@Scope
@MustBeDocumented
@Retention(RUNTIME)
annotation class RecipeDetailScope

@Scope
@MustBeDocumented
@Retention(RUNTIME)
annotation class RecipeListScope

