package com.magiag.androidchallenge.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.magiag.androidchallenge.data.database.ShowRoomDatabase
import com.magiag.androidchallenge.data.repository.model.ShowsModelStore
import com.magiag.androidchallenge.data.entity.ShowEntity
import com.magiag.androidchallenge.data.repository.data.ShowsDataRepository
import com.magiag.androidchallenge.data.repository.data.ShowsDataStore
import com.magiag.androidchallenge.data.repository.model.ShowsModelRepository
import com.magiag.androidchallenge.viewmodel.base.BaseViewModel
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
    private val mShowsModelRepository: ShowsModelRepository
    private var mParentJob = Job()
    private val mCoroutineContext: CoroutineContext
        get() = mParentJob + Dispatchers.Main
    private val mScope = CoroutineScope(mCoroutineContext)

    init {
        mShowsDataRepository = ShowsDataStore()
        mShowsModelRepository = ShowsModelStore(ShowRoomDatabase
                .getDatabase(application)
                .showDao()
        )
    }

    fun OnShowsResult(): MutableLiveData<List<ShowEntity>> {
        return mOnShowsResult
    }

    fun insertAll(show: ShowEntity) = mScope.launch(Dispatchers.IO) {
        mShowsModelRepository.insertShow(show)
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
        mParentJob.cancel()
    }
}