package com.hlopezg.data_remote.networking.service

import com.hlopezg.data_remote.networking.PostsApiModel
import retrofit2.http.GET

interface HitService {
    @GET("/api/v1/search_by_date?query=mobile")
    suspend fun getPosts(): PostsApiModel
}