package com.hlopezg.data_remote.source

import com.hlopezg.data_remote.networking.HitApiModel
import com.hlopezg.data_remote.networking.service.HitService
import com.hlopezg.data_repository.data_source.remote.RemoteHitDataSource
import com.hlopezg.domain.entity.Hit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class RemoteHitDataSourceImpl(
    val hitService: HitService,
) : RemoteHitDataSource {
    override fun getHits(): Flow<List<Hit>> = flow {
        emit(hitService.getPosts().hitApiModels)
    }.map { hitList ->
        hitList.map {
            convert(it)
        }
    }

    private fun convert(hit: HitApiModel) =
        Hit(
            author = hit.author,
            comment_text = hit.comment_text,
            created_at = hit.created_at,
            story_id = hit.story_id,
            story_title = hit.story_title,
            story_url = hit.story_url,
            updated_at = hit.updated_at,
        )

}