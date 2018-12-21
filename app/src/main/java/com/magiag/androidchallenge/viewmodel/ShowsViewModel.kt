package com.magiag.androidchallenge.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magiag.androidchallenge.data.database.ShowRoomDatabase
import com.magiag.androidchallenge.data.repository.model.ShowsModelStore
import com.magiag.androidchallenge.data.entity.ShowEntity
import com.magiag.androidchallenge.data.repository.data.ShowsDataRepository
import com.magiag.androidchallenge.data.repository.data.ShowsDataStore
import com.magiag.androidchallenge.data.repository.model.ShowsModelRepository
import com.magiag.androidchallenge.viewmodel.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class ShowsViewModel(application: Application) : BaseViewModel(application) {
    private val mOnShowsResult = MutableLiveData<MutableList<ShowEntity>>()
    private val mOnShowsError = MutableLiveData<ShowsError>()
    private val mOnSavingShowsError = MutableLiveData<ShowEntity>()
    private val mShowList: MutableList<ShowEntity>?
    private val mShowsDataRepository: ShowsDataRepository
    private val mShowsModelRepository: ShowsModelRepository
    private var mParentJob = Job()
    private val mCoroutineContext: CoroutineContext
        get() = mParentJob + Dispatchers.Main
    private val mScope = CoroutineScope(mCoroutineContext)

    init {
        mShowList = ArrayList()
        mShowsDataRepository = ShowsDataStore()
        mShowsModelRepository = ShowsModelStore(ShowRoomDatabase
                .getDatabase(application)
                .showDao()
        )
    }

    fun onShowsResult(): MutableLiveData<MutableList<ShowEntity>> {
        return mOnShowsResult
    }

    fun onShowsError(): LiveData<ShowsError> {
        return mOnShowsError
    }

    fun onSavingShowsError(): LiveData<ShowEntity> {
        return mOnSavingShowsError
    }

    fun insertShow(show: ShowEntity) = mScope.launch(Dispatchers.IO) {
        try {
            mShowsModelRepository.insertShow(show)
        } catch (e: Exception) {
            Log.e(ShowsViewModel::class.java.simpleName, e.message)
            mOnSavingShowsError.postValue(show)
        }
    }

    fun getShowById(showId: Int): ShowEntity {
        return mShowsModelRepository.getShowById(showId)
    }

    fun getShows(page: Int): Disposable {
        return mShowsDataRepository.getShows(page)
                .flatMap { it -> Observable.fromIterable(it) }
                .filter { isAvaliable(it.id!!) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ mShowList!!.add(it) },
                        {
                            mOnShowsError.postValue(ShowsError.UNEXPECTED_ERROR)
                        },
                        {
                            mOnShowsResult.postValue(mShowList)
                        }
                )
    }

    fun isAvaliable(id: Int): Boolean {
        val baseShow = getShowById(id)
        if (baseShow == null) return true
        return id.compareTo(baseShow.id!!) != 0
    }

    override fun onCleared() {
        super.onCleared()
        mParentJob.cancel()
    }

    enum class ShowsError {
        UNEXPECTED_ERROR
    }
}