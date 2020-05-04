package com.dopezebraevm.nursehomeassistant.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Evgeniy Mezentsev on 04.05.2020.
 */
@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(task: TaskDB)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertList(u: List<TaskDB>)

    @Query("SELECT * FROM task")
    fun getAll(): List<TaskDB>

    @Query("SELECT * FROM task WHERE date > :startAt AND date < :endAt")
    fun getAll(startAt: Long, endAt: Long): List<TaskDB>

    @Query("DELETE FROM task WHERE task_id = :taskId")
    fun removeTask(taskId: String)

    @Query("DELETE FROM task")
    fun deleteAll()
}