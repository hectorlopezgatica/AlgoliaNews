package com.hlopezg.domain.usecase

import com.hlopezg.domain.entity.Hit
import com.hlopezg.domain.entity.Posts
import com.hlopezg.domain.repository.HitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHitsUseCase(
    configuration: Configuration,
    private val hitRepository: HitRepository,
) : UseCase<GetHitsUseCase.Request, GetHitsUseCase.Response>(configuration) {

    object Request : UseCase.Request
    data class Response(val post: Posts) : UseCase.Response

    override fun process(request: Request): Flow<Response> =
        hitRepository.getHits()
            .map {
                Response(
                    Posts(
                        hitApiModels = it,
                        hitsPerPage = 20,
                        page = 1,
                    )
                )
            }
}