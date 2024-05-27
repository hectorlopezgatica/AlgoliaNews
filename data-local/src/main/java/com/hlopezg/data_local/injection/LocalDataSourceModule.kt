package com.hlopezg.data_local.injection

import com.hlopezg.data_local.source.LocalHitDataSourceImpl
import com.hlopezg.data_repository.data_source.local.LocalHitDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun bindHitLocalDataSource(hitLocalDataSource: LocalHitDataSourceImpl): LocalHitDataSource
}