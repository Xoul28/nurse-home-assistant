package com.dopezebraevm.nursehomeassistant.view.plan

import com.dopezebraevm.nursehomeassistant.view.TaskVO

/**
 * Created by Evgeniy Mezentsev on 04.05.2020.
 */
data class ManageTaskVO(
    val id: String,
    val title: String = "",
    val type: String = "",
    val description: String = "",
    val whenExecute: String = ""
)
