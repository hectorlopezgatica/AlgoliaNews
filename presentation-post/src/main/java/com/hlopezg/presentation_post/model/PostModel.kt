package com.hlopezg.presentation_post.model

data class PostModel(
    val hitApiModels: List<HitModel> = emptyList(),
    val hitsPerPage: Int = 0,
    val page: Int = 0,
)
