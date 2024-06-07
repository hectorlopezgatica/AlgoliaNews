package com.hlopezg.domain.repository

import com.hlopezg.domain.entity.Hit
import com.hlopezg.domain.entity.Posts
import kotlinx.coroutines.flow.Flow

interface HitRepository {
    suspend fun getRemoteHits(): Flow<Posts>
    fun getLocalHits(): Flow<List<Hit>>
    fun getHit(id: Int): Flow<Hit>
    suspend fun saveHits(hits: List<Hit>)
    suspend fun deleteHit(hits: Hit): Int
}