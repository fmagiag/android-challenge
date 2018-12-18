package com.magiag.androidchallenge.data.services.api

import com.magiag.androidchallenge.data.entity.ShowEntity
import com.magiag.androidchallenge.data.services.RetrofitInitializer
import io.reactivex.Observable;

class TvmazeAPIService {

    fun getShows(page: Int): Observable<List<ShowEntity>> {
        return RetrofitInitializer().getTvmazeAPI().getShows(page)
    }
}
