package com.magiag.androidchallenge.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryEntity(
        val code: String? = null,
        val name: String? = null,
        val timezone: String? = null
) : Parcelable