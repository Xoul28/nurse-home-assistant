package com.dopezebraevm.nursehomeassistant.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Evgeniy Mezentsev on 04.05.2020.
 */
@Dao
interface PlanDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(plan: PlanDB)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertList(u: List<PlanDB>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateList(u: List<PlanDB>)

    @Query("SELECT * FROM `plan`")
    fun getAll(): List<PlanDB>?

    @Query("DELETE FROM `plan` WHERE plan_id = :planId")
    fun removePlan(planId: String)
}