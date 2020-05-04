package com.dopezebraevm.nursehomeassistant.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PressureDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(pressureDB: MeasurePressureDB)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertList(u: List<MeasurePressureDB>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateList(u: List<MeasurePressureDB>)

    @Query("SELECT * FROM pressure")
    fun getAll(): List<MeasurePressureDB>?

    @Query("DELETE FROM pressure WHERE id = :pressureId")
    fun removePlan(pressureId: Long)
}