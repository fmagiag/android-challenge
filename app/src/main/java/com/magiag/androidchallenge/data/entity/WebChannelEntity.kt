package com.magiag.androidchallenge.data.entity
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WebChannelEntity(
        val country: CountryEntity? = null,
        val id: Int? = null,
        val name: String? = null
) : Parcelable