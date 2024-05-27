package com.hlopezg.data_remote.networking

data class PostsApiModel(
    val hitApiModels: List<HitApiModel>,
    val hitsPerPage: Int,
    val page: Int,
)