package com.dopezebraevm.nursehomeassistant.data.pref

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

/**
 * Created by Evgeniy Mezentsev on 04.05.2020.
 */
class PrefHelper(context: Context, mode: Int = MODE_PRIVATE) {

    private val APP_PREFERENCES = "nurse-data"

    private var preferences: SharedPreferences = context.getSharedPreferences(APP_PREFERENCES, mode)

    companion object {
        const val IS_NOT_FIRST_START = "not_first_start"
        const val GRAPH = "graph"
    }

    fun putString(key: String?, value: String?) {
        preferences.edit().putString(key, value).apply()
    }

    fun getString(key: String?): String? {
        return preferences.getString(key, null)
    }

    private fun getString(key: String, default: String): String {
        return preferences.getString(key, default) ?: default
    }

    fun putBoolean(key: String?, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String?): Boolean {
        return preferences.getBoolean(key, false)
    }

    fun putInt(key: String?, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }

    fun getInt(key: String?, defValue: Int): Int {
        return preferences.getInt(key, defValue)
    }

    fun putLong(key: String?, value: Long) {
        preferences.edit().putLong(key, value).apply()
    }

    fun getLong(key: String?, defValue: Long): Long {
        return preferences.getLong(key, defValue)
    }

    fun clear() {
        preferences.edit().clear().apply()
    }

    fun isNotFirstStart(): Boolean {
        return getBoolean(IS_NOT_FIRST_START)
    }

    fun putNotFirstStart(isNotFirstStart: Boolean) {
        putBoolean(IS_NOT_FIRST_START, isNotFirstStart)
    }

    fun isGraph(): Boolean {
        return getBoolean(GRAPH)
    }

    fun putGraph(isStart: Boolean) {
        putBoolean(GRAPH, isStart)
    }
}
