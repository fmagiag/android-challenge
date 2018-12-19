package com.magiag.androidchallenge.data.repository.model

import androidx.annotation.WorkerThread
import com.magiag.androidchallenge.data.entity.ShowEntity

import androidx.lifecycle.LiveData
import com.magiag.androidchallenge.data.database.dao.ShowDao

class ShowsModelStore(private val showDao: ShowDao) : ShowsModelRepository {

    override fun getAllShows(): LiveData<List<ShowEntity>> {
        return showDao.getAll()
    }

    @WorkerThread
    override suspend fun deleteAllShows() {
        showDao.deleteAll()
    }

    override fun getShowById(showId: Int): ShowEntity {
        return showDao.getShowById(showId)
    }

    @WorkerThread
    override suspend fun insertShow(show: ShowEntity) {
        showDao.insertShow(show)
    }

    @WorkerThread
    override suspend fun insertAll(shows: List<ShowEntity>) {
        showDao.insertAll(shows)
    }

    @WorkerThread
    override suspend fun deleteShow(show: ShowEntity) {
        showDao.deleteShow(show)
    }

    override fun getCount(): Int {
        return showDao.getCount()
    }
}
