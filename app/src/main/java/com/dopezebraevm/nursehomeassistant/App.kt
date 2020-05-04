package com.dopezebraevm.nursehomeassistant

import android.app.Application
import android.content.Context
import com.dopezebraevm.nursehomeassistant.data.db.AppDatabase
import com.dopezebraevm.nursehomeassistant.data.db.DataBaseBuilderRoom
import com.dopezebraevm.nursehomeassistant.domain.GenerateTaskInteractor

/**
 * Created by Evgeniy Mezentsev on 04.05.2020.
 */
class App : Application() {

    companion object {

        @JvmStatic
        fun get(context: Context): App {
            return context.applicationContext as App
        }
    }

    lateinit var dataBase: AppDatabase
    lateinit var generateTaskInteractor: GenerateTaskInteractor

    override fun onCreate() {
        super.onCreate()

        dataBase = DataBaseBuilderRoom(this).build()
        generateTaskInteractor = GenerateTaskInteractor(dataBase)
        generateTaskInteractor.generate()
    }

}