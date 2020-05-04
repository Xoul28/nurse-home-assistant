package com.dopezebraevm.nursehomeassistant.view

import java.util.*

/**
 * Created by Evgeniy Mezentsev on 03.05.2020.
 */
data class DayVO(
    val day: Int,
    val tasks: List<TaskVO>
)

data class TaskVO(
    val id: String,
    val type: Int,
    val title: String = "",
    val description: String = "",
    val whenExecute: String = "",
    val taskType: String = "",
    var completeType: Int = EXECUTE
) {

    companion object {
        const val TASK = 0
        const val SEPARATOR = 1

        const val EXECUTE = 0
        const val COMPLETE = 1
        const val SKIP = 2
    }

    fun sortField(): Int {
        return when (whenExecute.toLowerCase(Locale.ROOT)) {
            "утром" -> 0
            "днем" -> 1
            "вечером" -> 2
            else -> 3
        }
    }
}

data class CalendarVO(
    val id: Int,
    val isClickable: Boolean
)
