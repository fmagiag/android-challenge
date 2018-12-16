package com.magiag.androidchallenge.data.entity
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageEntity(
        val medium: String? = null,
        val original: String? = null
) : Parcelable