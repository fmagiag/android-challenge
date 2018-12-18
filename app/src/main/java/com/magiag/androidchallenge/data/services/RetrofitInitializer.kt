package com.magiag.androidchallenge.data.services

import com.google.gson.GsonBuilder
import com.magiag.androidchallenge.data.services.api.TvmazeAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    val BASE_API = "http://api.tvmaze.com"
    val mRetrofit: Retrofit

    init {
        val logging  = HttpLoggingInterceptor()
        logging .level = HttpLoggingInterceptor.Level.BODY

        val gson = GsonBuilder()
                .setLenient()
                .create()

        val httpClient = OkHttpClient.Builder()
                .addInterceptor(logging )
                .build()

        mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_API)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build()
    }

    fun getTvmazeAPI(): TvmazeAPI {
        return mRetrofit.create(TvmazeAPI::class.java)
    }
}
