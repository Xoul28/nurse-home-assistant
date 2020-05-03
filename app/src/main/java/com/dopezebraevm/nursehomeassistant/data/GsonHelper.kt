package com.dopezebraevm.nursehomeassistant.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonHelper {

    fun getGson() : Gson {
        return GsonBuilder().create()
    }
}