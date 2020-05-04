package com.dopezebraevm.nursehomeassistant.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Evgeniy Mezentsev on 04.05.2020.
 */
@Database(
    entities = [
        TaskDB::class,
        PlanDB::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
    abstract fun getPlanDao(): PlanDao
}