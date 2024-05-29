package com.hlopezg.domain.entity

data class Posts (
    val hitApiModels: List<Hit>,
    val hitsPerPage: Int,
    val page: Int,
)