package com.hlopezg.domain.entity

data class Hit(
    val author: String,
    val createdAt: String,
    val storyId: Int,
    val storyTitle: String,
    val storyUrl: String,
    val updatedAt: String,
    val title: String?,
)