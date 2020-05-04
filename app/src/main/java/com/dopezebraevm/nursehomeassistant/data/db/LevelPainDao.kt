package com.dopezebraevm.nursehomeassistant.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LevelPainDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(levelPain: LevelPainDB)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertList(u: List<LevelPainDB>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateList(u: List<LevelPainDB>)

    @Query("SELECT * FROM levelpain")
    fun getAll(): List<LevelPainDB>?

    @Query("DELETE FROM levelpain WHERE id = :id")
    fun removePlan(id: Long)
}