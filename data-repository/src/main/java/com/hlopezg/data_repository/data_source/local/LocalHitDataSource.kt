package com.hlopezg.data_repository.data_source.local

import com.hlopezg.domain.entity.Hit
import kotlinx.coroutines.flow.Flow

interface LocalHitDataSource {
    fun getHits(): Flow<List<Hit>>
    fun getHit(id: Int): Flow<Hit>
    fun saveHits(hit: List<Hit>)
}