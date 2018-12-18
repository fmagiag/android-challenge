package com.magiag.androidchallenge.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magiag.androidchallenge.data.database.ShowRoomDatabase
import com.magiag.androidchallenge.data.database.ShowsBDRepository
import com.magiag.androidchallenge.data.entity.ShowEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


class FavoritesViewModel(application: Application) : BaseViewModel(application){
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    private val repository: ShowsBDRepository

    init {
        val showDao = ShowRoomDatabase.getDatabase(application, scope).showDao()
        repository = ShowsBDRepository(showDao)
    }

    fun getAllShows(): LiveData<List<ShowEntity>>{
        return repository.getAllShows()
    }
}
