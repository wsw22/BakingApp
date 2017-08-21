package com.example.wsw.bakingapp.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = Recipe.TABLE_NAME)
data class Recipe(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = ID) val id: Int?,
    @ColumnInfo(name = NAME) val name: String,
    @ColumnInfo(name = SERVINGS) val servings: Int,
    @ColumnInfo(name = IMAGE_URI) val imageUri: String?) {

  companion object {
    const val TABLE_NAME = "recipe"
    const val ID = "id"
    const val NAME = "name"
    const val SERVINGS = "servings"
    const val IMAGE_URI = "image"
  }

}