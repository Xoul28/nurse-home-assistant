package com.dopezebraevm.nursehomeassistant.view

/**
 * Created by Evgeniy Mezentsev on 03.05.2020.
 */
data class DayVO(
    val day: Int,
    val tasks: List<TaskVO>
)

data class TaskVO(
    val id: String,
    val title: String,
    val description: String,
    val whenExecute: String
)

data class CalendarVO(
    val id: Int
)
