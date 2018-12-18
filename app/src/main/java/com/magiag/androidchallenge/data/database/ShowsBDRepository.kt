package com.magiag.androidchallenge.data.database

import androidx.annotation.WorkerThread
import com.magiag.androidchallenge.data.entity.ShowEntity

import androidx.lifecycle.LiveData

class ShowsBDRepository(private val showDao: ShowDao) {

    fun getAllShows(): LiveData<List<ShowEntity>>{
        return showDao.getAll()
    }

    fun deleteAllShows() {
        showDao.deleteAll()
    }

    fun getShowById(showId: Int): ShowEntity {
        return showDao.getShowById(showId)
    }

    fun insertShow(show: ShowEntity) {
        showDao.insertShow(show)
    }

    fun insertAll(shows: List<ShowEntity>){
        showDao.insertAll(shows)
    }

    fun deleteShow(show: ShowEntity) {
        showDao.deleteShow(show)
    }

    fun getCount(): Int{
        return showDao.getCount()
    }
}
