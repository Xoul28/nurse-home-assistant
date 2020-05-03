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
    val type: Int,
    val title: String = "",
    val description: String = "",
    val whenExecute: String = "",
    var completeType: Int = EXECUTE
) {

    companion object {
        const val TASK = 0
        const val SEPARATOR = 1

        const val EXECUTE = 0
        const val COMPLETE = 1
        const val SKIP = 2
    }
}

data class CalendarVO(
    val id: Int
)
