package com.magiag.androidchallenge.data.repository.model

import androidx.lifecycle.LiveData
import com.magiag.androidchallenge.data.entity.ShowEntity

interface ShowsModelRepository {

    fun getAllShows(): LiveData<List<ShowEntity>>

    fun deleteAllShows()

    fun getShowById(showId: Int): ShowEntity

    fun insertShow(show: ShowEntity)

    fun insertAll(shows: List<ShowEntity>)

    fun deleteShow(show: ShowEntity)

    fun getCount(): Int
}