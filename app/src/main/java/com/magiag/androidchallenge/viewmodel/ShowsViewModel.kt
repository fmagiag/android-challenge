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
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class ShowsViewModel(application: Application) : BaseViewModel(application) {
    private val mOnShowsResult = MutableLiveData<List<ShowEntity>>()
    private val showList: MutableList<ShowEntity>?
    private val mShowsDataRepository: ShowsDataRepository
    private val mShowsModelRepository: ShowsModelRepository
    private var mParentJob = Job()
    private val mCoroutineContext: CoroutineContext
        get() = mParentJob + Dispatchers.Main
    private val mScope = CoroutineScope(mCoroutineContext)

    init {
        showList = ArrayList()
        mShowsDataRepository = ShowsDataStore()
        mShowsModelRepository = ShowsModelStore(ShowRoomDatabase
                .getDatabase(application)
                .showDao()
        )
    }

    fun onShowsResult(): MutableLiveData<List<ShowEntity>> {
        return mOnShowsResult
    }

    fun insertShow(show: ShowEntity) = mScope.launch(Dispatchers.IO) {
        try {
            mShowsModelRepository.insertShow(show)
        } catch (e: Exception) {
            Log.e(ShowsViewModel::class.java.simpleName, e.message)
        }
    }

    fun getShowById(showId: Int): ShowEntity {
        return mShowsModelRepository.getShowById(showId)
    }

    fun getShows(page: Int): Disposable {
        return mShowsDataRepository.getShows(page)
                .flatMap { it -> Observable.fromIterable(it) }
                .filter { it != getShowById(it.id!!) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    showList!!.add(it)
                }, { e ->
                    Log.e("onError error", e?.message)
                }, {
                    mOnShowsResult.postValue(showList)
                })
    }

    override fun onCleared() {
        super.onCleared()
        mParentJob.cancel()
    }
}