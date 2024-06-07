package com.hlopezg.data_local.source

import com.hlopezg.data_local.hit.HitDao
import com.hlopezg.data_local.mapper.toDomain
import com.hlopezg.data_local.mapper.toEntity
import com.hlopezg.data_repository.data_source.local.LocalHitDataSource
import com.hlopezg.domain.entity.Hit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalHitDataSourceImpl @Inject constructor(
    private val hitDao: HitDao,
) : LocalHitDataSource {
    override fun getHits(): Flow<List<Hit>> = hitDao.getAll().map { hits ->
        hits.map {
            it.toDomain()
        }
    }

    override fun getHit(id: Int): Flow<Hit> = hitDao.getById(id).map {
        it.toDomain()
    }

    override suspend fun saveHits(hit: List<Hit>) =
        hitDao.save(hit.map { it ->
            it.toEntity()
        })

    override suspend fun delete(id: Int) = hitDao.delete(id)

}