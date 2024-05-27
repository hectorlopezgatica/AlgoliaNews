package com.hlopezg.data_repository.data_source.remote

import com.hlopezg.domain.entity.Hit
import kotlinx.coroutines.flow.Flow

interface RemoteHitDataSource {
    fun getHits(): Flow<List<Hit>>
}