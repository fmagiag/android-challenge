package com.magiag.androidchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.magiag.androidchallenge.data.entity.ShowEntity
import com.magiag.androidchallenge.data.repository.ShowsDataRepository
import com.magiag.androidchallenge.data.repository.ShowsDataStore
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ShowsViewModel : BaseViewModel() {
    private val mOnShowsResult = MutableLiveData<List<ShowEntity>>()
    private val mShowsDataRepository: ShowsDataRepository

    init {
        mShowsDataRepository = ShowsDataStore()
    }

    fun OnShowsResult(): MutableLiveData<List<ShowEntity>> {
        return mOnShowsResult
    }

    fun getShows(page: Int): Disposable {
        return  mShowsDataRepository.getShows(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({shows ->
                    mOnShowsResult.postValue(shows)
                }, { e ->
                    Log.e("onError error", e?.message)
                },{
                    Log.e("onComplete success", "ok")
                })
    }
}