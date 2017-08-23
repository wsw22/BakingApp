package com.example.wsw.bakingapp.di

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.PROPERTY_GETTER
import kotlin.annotation.AnnotationTarget.PROPERTY_SETTER
import kotlin.reflect.KClass

/**
 * Created by wsw on 17-8-23.
 */
@MustBeDocumented
@Target(FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER)
@Retention(RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)