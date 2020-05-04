package com.dopezebraevm.nursehomeassistant.data.db

import android.content.Context
import androidx.room.Room
import com.dopezebraevm.nursehomeassistant.BuildConfig

/**
 * Created by Evgeniy Mezentsev on 04.05.2020.
 */
class DataBaseBuilderRoom(private val context: Context) {

    private var appDataBase: AppDatabase? = null

    fun build(): AppDatabase {
        if (appDataBase == null) {
            appDataBase = Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.Database_name)
                .allowMainThreadQueries()
                .build()
        }
        return appDataBase ?: throw IllegalArgumentException("Error creating database")
    }

    fun migrate() {
        // TODO: migration db with recreation
    }
}
