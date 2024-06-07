package com.hlopezg.data_remote.source

import com.hlopezg.data_remote.networking.HitApiModel
import com.hlopezg.data_remote.networking.service.HitService
import com.hlopezg.data_repository.data_source.remote.RemoteHitDataSource
import com.hlopezg.domain.entity.Hit
import com.hlopezg.domain.entity.Posts
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
    override fun getHits(): Flow<Posts> = flow {
        emit(hitService.getPosts())
    }.map { post ->
        val hits = post.hitApiModels.map {
            convert(it)
        }
        Posts(
            hitApiModels = hits,
            hitsPerPage = post.hitsPerPage,
            page = post.page,
        )
    }.catch {
        if(it is UnknownHostException){
            throw UseCaseException.UnknownHostException(it)
        }else{
            throw UseCaseException.PostException(it)
        }
    }

/*    override suspend fun getHits(): List<Hit> {
        try{
            return hitService.getPosts().hitApiModels.map { hitList ->
                convert(hitList)
            }
        }catch (e: Exception){
            if(e is UnknownHostException){
                throw UseCaseException.UnknownHostException(e)
            }else{
                throw UseCaseException.PostException(e)
            }
        }
    }*/

    private fun convert(hit: HitApiModel) = Hit(
        author = hit.author,
        createdAt = hit.createdAt,
        storyId = hit.storyId,
        storyTitle = hit.storyTitle ?: hit.title,
        storyUrl = hit.storyUrl ?: "",
        updatedAt = hit.updatedAt,
        title = hit.title,
    )
}