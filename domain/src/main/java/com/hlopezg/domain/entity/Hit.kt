package com.hlopezg.domain.entity

data class Hit(
    val author: String,
    val created_at: String,
    val story_id: Int,
    val story_title: String,
    val story_url: String,
    val updated_at: String,
    val title: String?,
)