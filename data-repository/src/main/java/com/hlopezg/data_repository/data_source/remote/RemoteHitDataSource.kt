package com.hlopezg.data_repository.data_source.remote

import com.hlopezg.domain.entity.Posts
import kotlinx.coroutines.flow.Flow

interface RemoteHitDataSource {
    fun getHits(): Flow<Posts>
}