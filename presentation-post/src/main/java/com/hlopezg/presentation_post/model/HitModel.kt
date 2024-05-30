package com.hlopezg.presentation_post.model

import java.util.Date

data class HitModel (
    val author: String,
    val createdAt: String,
    val storyId: Int,
    val storyTitle: String,
    val storyUrl: String,
    val updatedAt: String,
)