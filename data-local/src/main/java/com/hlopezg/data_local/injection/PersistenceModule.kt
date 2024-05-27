package com.hlopezg.data_local.injection

import android.content.Context
import androidx.room.Room
import com.hlopezg.data_local.AppDatabase
import com.hlopezg.data_local.hit.HitDao
import com.hlopezg.data_local.source.LocalHitDataSourceImpl
import com.hlopezg.data_repository.data_source.local.LocalHitDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "my-database",
        ).build()
    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): HitDao = appDatabase.hitDao()
}