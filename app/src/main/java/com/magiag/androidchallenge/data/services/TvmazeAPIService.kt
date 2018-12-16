package com.magiag.androidchallenge.data.services

import com.magiag.androidchallenge.data.entity.ShowEntity
import io.reactivex.Observable;

class TvmazeAPIService {

    fun getShows(page: Int): Observable<List<ShowEntity>> {
        return RetrofitInitializer().getTvmazeAPI().getShows(page)
    }
}
