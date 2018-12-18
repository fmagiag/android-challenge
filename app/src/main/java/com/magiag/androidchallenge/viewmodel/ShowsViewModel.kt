package com.magiag.androidchallenge.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.magiag.androidchallenge.data.database.ShowRoomDatabase
import com.magiag.androidchallenge.data.database.ShowsBDRepository
import com.magiag.androidchallenge.data.entity.ShowEntity
import com.magiag.androidchallenge.data.repository.ShowsDataRepository
import com.magiag.androidchallenge.data.repository.ShowsDataStore
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ShowsViewModel(application: Application) : BaseViewModel(application) {
    private val mOnShowsResult = MutableLiveData<List<ShowEntity>>()
    private val mShowsDataRepository: ShowsDataRepository
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    private val repository: ShowsBDRepository

    init {
        mShowsDataRepository = ShowsDataStore()
        val showDao = ShowRoomDatabase.getDatabase(application, scope).showDao()
        repository = ShowsBDRepository(showDao)
    }

    fun OnShowsResult(): MutableLiveData<List<ShowEntity>> {
        return mOnShowsResult
    }

    fun insertAll(show: ShowEntity) = scope.launch(Dispatchers.IO) {
        repository.insertShow(show)
    }

    fun getShows(page: Int): Disposable {
        return mShowsDataRepository.getShows(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ shows ->
                    mOnShowsResult.postValue(shows)
                    insertAll(shows.get(0))
                }, { e ->
                    Log.e("onError error", e?.message)
                }, {
                    Log.e("onComplete success", "ok")
                })
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}