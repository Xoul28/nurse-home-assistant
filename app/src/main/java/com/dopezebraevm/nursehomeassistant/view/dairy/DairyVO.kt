package com.dopezebraevm.nursehomeassistant.view.dairy

import java.util.*

/**
 * Created by Evgeniy Mezentsev on 04.05.2020.
 */
data class DairyVO(
    val title: String,
    val middle: String,
    val last: List<String>,
    val points: List<Point>
)

data class Point(
    val point: Int,
    val date: Date
)
