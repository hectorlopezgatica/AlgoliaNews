package com.hlopezg.domain.usecase

import com.hlopezg.domain.entity.Posts
import com.hlopezg.domain.repository.HitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class GetRemoteHitsAndSaveItUseCase(
    configuration: UseCase.Configuration,
    private val hitRepository: HitRepository,
) : UseCase<GetRemoteHitsAndSaveItUseCase.Request, GetRemoteHitsAndSaveItUseCase.Response>(
    configuration
) {

    object Request : UseCase.Request
    data class Response(val posts: Posts) : UseCase.Response

    /*    suspend operator fun invoke() {
                val hits = hitRepository.getRemoteHits()
                hitRepository.saveHits(hits)
        }*/

    override fun process(request: Request): Flow<Response> = flow {
        hitRepository.getRemoteHits().map {
            hitRepository.saveHits(it.hitApiModels)
            flowOf(
                Response(
                    it
                )
            )
        }
    }
}