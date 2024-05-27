package com.hlopezg.data_local.hit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hlopezg.domain.entity.Hit
import kotlinx.coroutines.flow.Flow

@Dao
interface HitDao {
    @Query("SELECT * FROM hit")
    fun getAll(): Flow<List<HitEntity>>

    @Query("SELECT * FROM hit WHERE story_id = :id")
    fun getById(id: Int): Flow<HitEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(hits: List<HitEntity>)
}