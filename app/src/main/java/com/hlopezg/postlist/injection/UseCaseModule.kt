package com.hlopezg.postlist.injection

import com.hlopezg.domain.repository.HitRepository
import com.hlopezg.domain.usecase.GetHitUseCase
import com.hlopezg.domain.usecase.GetHitsUseCase
import com.hlopezg.domain.usecase.GetLocalHitsUseCase
import com.hlopezg.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun provideUseCaseConfiguration(): UseCase.Configuration = UseCase.Configuration(Dispatchers.IO)

    @Provides
    fun provideHitUseCase(
        configuration: UseCase.Configuration,
        hitRepository: HitRepository,
    ): GetHitUseCase = GetHitUseCase(
        configuration = configuration,
        hitRepository = hitRepository,
    )

    @Provides
    fun provideHitsUseCase(
        configuration: UseCase.Configuration,
        hitRepository: HitRepository,
    ): GetHitsUseCase = GetHitsUseCase(
        configuration = configuration,
        hitRepository = hitRepository,
    )

    @Provides
    fun provideLocalHitsUseCase(
        configuration: UseCase.Configuration,
        hitRepository: HitRepository,
    ): GetLocalHitsUseCase = GetLocalHitsUseCase(
        configuration = configuration,
        hitRepository = hitRepository,
    )

}