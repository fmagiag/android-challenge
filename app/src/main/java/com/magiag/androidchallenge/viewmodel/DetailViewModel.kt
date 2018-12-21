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

class DetailViewModel(application: Application) : BaseViewModel(application) {
    private val mShowsModelRepository: ShowsModelRepository
    private val mOnSavingShowsError = MutableLiveData<ShowEntity>()
    private val mOnDeletingShowsError = MutableLiveData<ShowEntity>()
    private var mParentJob = Job()
    private val mCoroutineContext: CoroutineContext
        get() = mParentJob + Dispatchers.Main
    private val mScope = CoroutineScope(mCoroutineContext)

    init {
        mShowsModelRepository = ShowsModelStore(ShowRoomDatabase
                .getDatabase(application)
                .showDao()
        )
    }
    fun deleteShow(show: ShowEntity) = mScope.launch(Dispatchers.IO) {
        try { mShowsModelRepository.deleteShow(show) } catch (e: Exception) {
            Log.e(FavoritesViewModel::class.java.simpleName, e.message)
            mOnDeletingShowsError.postValue(show)
        }
    }

    fun insertShow(show: ShowEntity) = mScope.launch(Dispatchers.IO) {
        try {
            mShowsModelRepository.insertShow(show)
        } catch (e: Exception) {
            Log.e(ShowsViewModel::class.java.simpleName, e.message)
            mOnSavingShowsError.postValue(show)
        }
    }

    fun onSavingShowsError(): LiveData<ShowEntity> {
        return mOnSavingShowsError
    }

    fun onDeletingShowsError(): LiveData<ShowEntity> {
        return mOnDeletingShowsError
    }
}