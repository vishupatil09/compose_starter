package com.compose_demo.vishaldemo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.compose_demo.vishaldemo.data.local.dao.DataDao
import com.compose_demo.vishaldemo.data.local.dao.DatabaseDao


@Database(
    entities =
        [
        ], version = 1, exportSchema = true
)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun databaseDao(): DatabaseDao
    abstract fun dataDao(): DataDao

    companion object {
        private var instance: LocalDatabase? = null
        fun getInstance(appContext: Context): LocalDatabase {
            if (instance == null) {
                synchronized(LocalDatabase::class.java) {
                    instance =
                        Room.databaseBuilder(appContext, LocalDatabase::class.java, "LocalMain.DB")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return instance!!
        }
    }
}