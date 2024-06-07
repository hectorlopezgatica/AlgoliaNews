package com.hlopezg.domain.usecase

import com.hlopezg.domain.entity.Posts
import com.hlopezg.domain.repository.HitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRemoteHitsAndSaveItUseCase(
    configuration: UseCase.Configuration,
    private val hitRepository: HitRepository,
) : UseCase<GetRemoteHitsAndSaveItUseCase.Request, GetRemoteHitsAndSaveItUseCase.Response>(
    configuration
) {
    object Request : UseCase.Request
    data class Response(val posts: Posts) : UseCase.Response

    override fun process(request: Request): Flow<Response> =
        hitRepository.getRemoteHits().map {
            Response(
                it
            )
        }
}