package com.hlopezg.data_local.hit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HitDao {
    @Query("SELECT * FROM hit WHERE deleted = 0 ORDER BY updated_at DESC ")
    fun getAll(): Flow<List<HitEntity>>

    @Query("SELECT * FROM hit WHERE story_id = :id AND deleted = 0")
    fun getById(id: Int): Flow<HitEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(hits: List<HitEntity>)

    @Query("UPDATE hit SET deleted = 1 WHERE story_id = :id")
    suspend fun delete(id: Int): Int
}