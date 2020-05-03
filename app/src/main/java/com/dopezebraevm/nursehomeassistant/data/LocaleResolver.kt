package com.dopezebraevm.nursehomeassistant.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocaleResolver {

    inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object: TypeToken<T>() {}.type)

    fun getIllList() : List<String> {
        val json = Data.getDirectoryList()

        val gson = GsonHelper.getGson()
        return gson.fromJson<List<String>>(json)
    }
}