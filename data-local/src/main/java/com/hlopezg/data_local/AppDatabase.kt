package com.hlopezg.data_local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hlopezg.data_local.hit.HitDao
import com.hlopezg.data_local.hit.HitEntity

@Database(entities = [HitEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun hitDao(): HitDao
}