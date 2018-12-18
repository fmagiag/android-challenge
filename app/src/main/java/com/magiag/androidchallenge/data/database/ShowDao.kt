package com.magiag.androidchallenge.data.database

import com.magiag.androidchallenge.data.entity.ShowEntity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShow(showEntity: ShowEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(shows: List<ShowEntity>)

    @Delete
    fun deleteShow(showEntity: ShowEntity)

    @Query("SELECT * FROM shows WHERE id = :id")
    fun getShowById(id: Int): ShowEntity

    @Query("SELECT * FROM shows ORDER BY name DESC")
    fun getAll(): LiveData<List<ShowEntity>>

    @Query("DELETE FROM shows")
    fun deleteAll(): Int

    @Query("SELECT COUNT(*) FROM shows")
    fun getCount(): Int
}
