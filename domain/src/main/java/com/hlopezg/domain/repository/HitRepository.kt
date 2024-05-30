package com.hlopezg.domain.repository

import com.hlopezg.domain.entity.Hit
import kotlinx.coroutines.flow.Flow

interface HitRepository {
    fun getHits(): Flow<List<Hit>>
    fun getLocalHits(): Flow<List<Hit>>
    fun getHit(id: Int): Flow<Hit>
    fun saveHits(hits: List<Hit>): Flow<List<Hit>>
}