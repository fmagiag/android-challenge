package com.magiag.androidchallenge.data.repository

import com.magiag.androidchallenge.data.entity.ShowEntity
import io.reactivex.Observable

interface ShowsDataRepository {
    fun getShows(page: Int): Observable<List<ShowEntity>>
}