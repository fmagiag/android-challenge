package com.magiag.androidchallenge.data.services

import com.magiag.androidchallenge.data.entity.ShowEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TvmazeAPI {
    @GET("/shows")
    fun getShows(@Query("page") page: Int): Observable<List<ShowEntity>>
}
