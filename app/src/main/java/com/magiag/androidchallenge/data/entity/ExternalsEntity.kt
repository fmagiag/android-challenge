package com.magiag.androidchallenge.data.entity
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExternalsEntity(
        val imdb: String? = null,
        val thetvdb: Int? = null,
        val tvrage: Int? = null
) : Parcelable