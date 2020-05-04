package com.dopezebraevm.nursehomeassistant.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pressure")
class MeasurePressureDB(

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "top_pressure")
    var topPressure: Int,

    @ColumnInfo(name = "bottom_pressure")
    var bottomPressure: Int
)