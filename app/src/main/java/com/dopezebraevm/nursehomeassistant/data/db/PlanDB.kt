package com.dopezebraevm.nursehomeassistant.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Created by Evgeniy Mezentsev on 04.05.2020.
 */
@Entity(
    tableName = "plan", indices = [
        Index(value = ["plan_id"], unique = true)
    ]
)

data class PlanDB(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "plan_id")
    var planId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "when")
    var whenExecute: String
)