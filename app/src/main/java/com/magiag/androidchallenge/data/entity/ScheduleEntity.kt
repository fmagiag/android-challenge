package com.magiag.androidchallenge.data.entity
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ScheduleEntity(
        val days: List<String>? = null,
        val time: String? = null
) : Parcelable