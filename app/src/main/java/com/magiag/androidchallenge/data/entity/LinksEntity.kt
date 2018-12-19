package com.magiag.androidchallenge.data.entity
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LinksEntity(
        val self: SelfEntity? = null
) : Parcelable