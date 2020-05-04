package com.dopezebraevm.nursehomeassistant.data

import android.content.Context

class EncyclopediaJson(
    val title: String,
    val description: String,
    val image_name: String
) {

    fun getImage(context: Context): Int {
        return context.resources
            .getIdentifier(image_name, "drawable", context.packageName)
    }
}