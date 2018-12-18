package com.magiag.androidchallenge.data.repository.data

import com.magiag.androidchallenge.data.entity.ShowEntity
import com.magiag.androidchallenge.data.services.api.TvmazeAPIService
import io.reactivex.Observable;

class ShowsDataStore: ShowsDataRepository {
    override fun getShows(page: Int): Observable<List<ShowEntity>> {
        return TvmazeAPIService().getShows(page)
    }
}