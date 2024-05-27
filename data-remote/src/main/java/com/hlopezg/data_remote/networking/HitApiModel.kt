package com.hlopezg.data_remote.networking

data class HitApiModel(
    val author: String,
    val comment_text: String,
    val created_at: String,
    val story_id: Int,
    val story_title: String,
    val story_url: String,
    val updated_at: String,
)