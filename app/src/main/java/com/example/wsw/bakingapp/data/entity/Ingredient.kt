package com.example.wsw.bakingapp.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(
    tableName = Ingredient.TABLE_NAME,
    indices = arrayOf(
        Index(
            value = Ingredient.RECIPE_ID,
            unique = true
        )),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Recipe::class,
            parentColumns = arrayOf(Recipe.ID),
            childColumns = arrayOf(Ingredient.RECIPE_ID)
        )
    ))
data class Ingredient(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = ID) val id: Int,
    @ColumnInfo(name = QUANTITY) val quantity: Double,
    @ColumnInfo(name = MEASURE) val measure: String,
    @ColumnInfo(name = NAME) val name: String,
    @ColumnInfo(name = RECIPE_ID) val recipe_id: Int) {

  companion object {
    const val TABLE_NAME = "ingredient"

    const val ID = "id"
    const val QUANTITY = "quantity"
    const val MEASURE = "measure"
    const val NAME = "name"

    const val RECIPE_ID = "recipe_id"
  }

}