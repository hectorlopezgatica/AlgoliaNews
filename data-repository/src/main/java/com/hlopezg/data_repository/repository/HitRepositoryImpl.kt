package com.hlopezg.data_repository.repository

import com.hlopezg.data_repository.data_source.local.LocalHitDataSource
import com.hlopezg.data_repository.data_source.remote.RemoteHitDataSource
import com.hlopezg.domain.entity.Hit
import com.hlopezg.domain.entity.Posts
import com.hlopezg.domain.repository.HitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class HitRepositoryImpl(
    private val localDataSource: LocalHitDataSource,
    private val remoteDataSource: RemoteHitDataSource,
) : HitRepository {
    override fun getRemoteHits(): Flow<Posts> =
        remoteDataSource.getHits().onEach {
            localDataSource.saveHits(it.hitApiModels)
        }


    override fun getLocalHits(): Flow<List<Hit>> = localDataSource.getHits()

    override fun getHit(id: Int): Flow<Hit> = localDataSource.getHit(id)

    override suspend fun saveHits(hits: List<Hit>) {
        localDataSource.saveHits(hits)
    }

    override suspend fun deleteHit(hits: Hit) = localDataSource.delete(hits.storyId)
}

