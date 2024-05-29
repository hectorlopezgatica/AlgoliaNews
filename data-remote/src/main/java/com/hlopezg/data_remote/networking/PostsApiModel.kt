package com.hlopezg.data_remote.networking

import com.google.gson.annotations.SerializedName

data class PostsApiModel(
    @SerializedName("hits") val hitApiModels: List<HitApiModel>,
    @SerializedName("hitsPerPage") val hitsPerPage: Int,
    @SerializedName("page") val page: Int,
)