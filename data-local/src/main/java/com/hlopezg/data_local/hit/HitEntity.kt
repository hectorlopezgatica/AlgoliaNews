package com.hlopezg.data_local.hit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hit")
data class HitEntity(
    @PrimaryKey
    @ColumnInfo("story_id")
    val storyId: Int,
    val author: String,
    @ColumnInfo("comment_text")
    val commentText: String,
    @ColumnInfo("created_at")
    val createdAt: String,
    @ColumnInfo("story_title")
    val storyTitle: String,
    @ColumnInfo("story_url")
    val storyUrl: String,
    @ColumnInfo("updated_at")
    val updatedAt: String,
)