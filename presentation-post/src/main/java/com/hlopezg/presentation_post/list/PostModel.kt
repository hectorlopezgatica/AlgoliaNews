package com.hlopezg.presentation_post.list

data class PostModel(
    val hitApiModels: List<HitModel>,
    val hitsPerPage: Int,
    val page: Int,
)
