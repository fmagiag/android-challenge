package com.magiag.androidchallenge.data.repository.model

import androidx.lifecycle.LiveData
import com.magiag.androidchallenge.data.entity.ShowEntity

interface ShowsModelRepository {

    fun getAllShows(): LiveData<List<ShowEntity>>

    suspend fun deleteAllShows()

    fun getShowById(showId: Int): ShowEntity

    suspend fun insertShow(show: ShowEntity)

    suspend fun insertAll(shows: List<ShowEntity>)

    suspend fun deleteShow(show: ShowEntity)

    fun getCount(): Int
}