package com.hlopezg.data_remote.injection

import com.hlopezg.data_remote.source.RemoteHitDataSourceImpl
import com.hlopezg.data_repository.data_source.remote.RemoteHitDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun bindRemoteDataSource(impl: RemoteHitDataSourceImpl): RemoteHitDataSource
}