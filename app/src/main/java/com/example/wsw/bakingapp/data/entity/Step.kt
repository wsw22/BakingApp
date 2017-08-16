package com.example.wsw.bakingapp.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(
    tableName = Step.TABLE_NAME,
    indices = arrayOf(
        Index(
            value = Step.RECIPE_ID,
            unique = true
        )),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Recipe::class,
            parentColumns = arrayOf(Recipe.ID),
            childColumns = arrayOf(Step.RECIPE_ID),
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        )
    ))
data class Step(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = ID) val id: Int,
    @ColumnInfo(name = SHORT_DESCRIPTION) val shortDescription: String,
    @ColumnInfo(name = DESCRIPTION) val description: String,
    @ColumnInfo(name = VIDEO_URL) val videoURL: String,
    @ColumnInfo(name = THUMBNAIL_URL) val thumbnailURL: String,
    @ColumnInfo(name = RECIPE_ID) val recipe_id: Int) {

  companion object {
    const val TABLE_NAME = "step"

    const val ID = "id"
    const val SHORT_DESCRIPTION = "short_description"
    const val DESCRIPTION = "description"
    const val VIDEO_URL = "video_url"
    const val THUMBNAIL_URL = "thumbnail_url"

    const val RECIPE_ID = "recipe_id"
  }
}