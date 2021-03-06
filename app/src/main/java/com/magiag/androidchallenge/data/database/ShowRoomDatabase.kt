package com.magiag.androidchallenge.data.database

import android.content.Context

import com.magiag.androidchallenge.data.entity.ShowEntity

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.magiag.androidchallenge.data.database.converter.Converter
import com.magiag.androidchallenge.data.database.dao.ShowDao
import kotlinx.coroutines.CoroutineScope

@Database(entities = [ShowEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class ShowRoomDatabase : RoomDatabase() {

    abstract fun showDao(): ShowDao

    companion object {
        val DATABASE_NAME = "ShowRoomDatabase.db"
        @Volatile
        private var INSTANCE: ShowRoomDatabase? = null

        fun getDatabase(context: Context): ShowRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShowRoomDatabase::class.java,
                        DATABASE_NAME
                ).build()
                INSTANCE = instance
//                return instance
                instance
            }
        }
    }
}

