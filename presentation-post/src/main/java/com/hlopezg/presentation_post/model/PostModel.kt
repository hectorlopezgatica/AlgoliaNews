package com.hlopezg.presentation_post.model

data class PostModel(
    val hitApiModels: MutableList<HitModel> = mutableListOf(),
    var page: Int = 0,
)
