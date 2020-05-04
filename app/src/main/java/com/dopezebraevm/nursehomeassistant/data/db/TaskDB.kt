package com.dopezebraevm.nursehomeassistant.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Created by Evgeniy Mezentsev on 04.05.2020.
 */
@Entity(
    tableName = "task", indices = [
        Index(value = ["task_id"], unique = true)
    ]
)
data class TaskDB(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "task_id")
    var taskId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "when")
    var whenExecute: String,

    @ColumnInfo(name = "completed_type")
    var completedType: Int,

    @ColumnInfo(name = "date")
    var date: Long
) {

    companion object {

        const val EXECUTE = 0
        const val COMPLETE = 1
        const val SKIP = 2
    }
}
