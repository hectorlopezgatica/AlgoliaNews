package com.hlopezg.domain.usecase

import com.hlopezg.domain.entity.Hit
import com.hlopezg.domain.repository.HitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeleteLocalHitUseCase(
    configuration: Configuration,
    private val hitRepository: HitRepository,
) : UseCase<DeleteLocalHitUseCase.Request, DeleteLocalHitUseCase.Response>(configuration) {

    data class Request(val hit: Hit) : UseCase.Request
    data class Response(val hit: Hit) : UseCase.Response

    override fun process(request: Request): Flow<Response> = flow {
        hitRepository.deleteHit(request.hit)
        emit(Response(request.hit))
    }
}