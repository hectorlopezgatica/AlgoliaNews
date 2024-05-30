package com.hlopezg.data_remote.source

import com.hlopezg.data_remote.networking.HitApiModel
import com.hlopezg.data_remote.networking.service.HitService
import com.hlopezg.data_repository.data_source.remote.RemoteHitDataSource
import com.hlopezg.domain.entity.Hit
import com.hlopezg.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.net.UnknownHostException
import javax.inject.Inject

class RemoteHitDataSourceImpl @Inject constructor(
    private val hitService: HitService,
) : RemoteHitDataSource {
    override fun getHits(): Flow<List<Hit>> = flow {
        emit(hitService.getPosts().hitApiModels)
    }.map { hitList ->
        hitList.map {
            convert(it)
        }
    }.catch {
        if(it is UnknownHostException){
            throw UseCaseException.UnknownHostException(it)
        }else{
            throw UseCaseException.PostException(it)
        }
    }

    private fun convert(hit: HitApiModel) = Hit(
        author = hit.author,
        created_at = hit.created_at,
        story_id = hit.story_id,
        story_title = hit.story_title ?: hit.title,
        story_url = hit.story_url ?: "",
        updated_at = hit.updated_at,
        title = hit.title,
    )
}