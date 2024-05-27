package com.hlopezg.data_repository.repository

import com.hlopezg.data_repository.data_source.local.LocalHitDataSource
import com.hlopezg.data_repository.data_source.remote.RemoteHitDataSource
import com.hlopezg.domain.entity.Hit
import com.hlopezg.domain.repository.HitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach

class HitRepositoryImpl(
    private val localDataSource: LocalHitDataSource,
    private val remoteDataSource: RemoteHitDataSource,
) : HitRepository {
    override fun getHits(): Flow<List<Hit>> =
        remoteDataSource.getHits().onEach {
            localDataSource.saveHits(it)
        }


    override fun getHit(id: Int): Flow<Hit> = localDataSource.getHit(id)

    override fun saveHits(hits: List<Hit>) = localDataSource.saveHits(hits)
}