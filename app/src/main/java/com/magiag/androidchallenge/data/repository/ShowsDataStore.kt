package com.magiag.androidchallenge.data.repository

import com.magiag.androidchallenge.data.entity.ShowEntity
import com.magiag.androidchallenge.data.services.TvmazeAPIService
import io.reactivex.Observable;

class ShowsDataStore: ShowsDataRepository{
    override fun getShows(page: Int): Observable<List<ShowEntity>> {
        return TvmazeAPIService().getShows(page)
    }
}