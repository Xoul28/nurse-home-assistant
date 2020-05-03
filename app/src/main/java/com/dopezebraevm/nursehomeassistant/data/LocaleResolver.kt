package com.dopezebraevm.nursehomeassistant.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object LocaleResolver {

    inline fun <reified T> Gson.fromJson(json: String) =
        fromJson<T>(json, object : TypeToken<T>() {}.type)

    fun getIllList(): List<String> {
        val json = Data.getDirectoryList()

        val gson = GsonHelper.getGson()
        return gson.fromJson<List<String>>(json)
    }

    fun getQuestionnaireList(): List<QuestionnaireJson> {
        val json = Data.getQuestionnaireData()
        val gson = GsonHelper.getGson()
        return gson.fromJson<List<QuestionnaireJson>>(json)
    }

    fun getTaskTemplateList(): List<TaskTemplateJson> {
        val json = Data.getTaskTemplates()
        val gson = GsonHelper.getGson()
        return gson.fromJson<List<TaskTemplateJson>>(json)
    }
}