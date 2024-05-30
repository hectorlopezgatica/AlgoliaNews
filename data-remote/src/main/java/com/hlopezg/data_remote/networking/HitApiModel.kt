package com.hlopezg.data_remote.networking

import com.google.gson.annotations.SerializedName

data class HitApiModel(
    val author: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("story_id") val storyId: Int,
    @SerializedName("story_title") val storyTitle: String?,
    val title: String,
    @SerializedName("story_url") val storyUrl: String?,
    @SerializedName("updated_at") val updatedAt: String,
)