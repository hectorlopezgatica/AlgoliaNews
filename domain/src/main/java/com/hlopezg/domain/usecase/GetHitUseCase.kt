package com.hlopezg.domain.usecase

import com.hlopezg.domain.entity.Hit
import com.hlopezg.domain.repository.HitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHitUseCase(
    configuration: Configuration,
    val hitRepository: HitRepository,
) :
    UseCase<GetHitUseCase.Request, GetHitUseCase.Response>(configuration) {

    data class Request(val id: Int) : UseCase.Request
    data class Response(val hit: Hit) : UseCase.Response

    override fun process(request: Request): Flow<Response> =
        hitRepository.getHit(request.id)
            .map {
              Response(it)
            }
}