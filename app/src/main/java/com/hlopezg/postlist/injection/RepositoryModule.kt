package com.hlopezg.postlist.injection

import com.hlopezg.data_repository.data_source.local.LocalHitDataSource
import com.hlopezg.data_repository.data_source.remote.RemoteHitDataSource
import com.hlopezg.data_repository.repository.HitRepositoryImpl
import com.hlopezg.domain.repository.HitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideRepository(
        remoteHitDataSource: RemoteHitDataSource,
        localHitDataSource: LocalHitDataSource,
    ): HitRepository = HitRepositoryImpl(
        remoteDataSource = remoteHitDataSource,
        localDataSource = localHitDataSource,
    )
}