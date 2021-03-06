package com.magiag.androidchallenge.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "shows")
data class ShowEntity(
        @PrimaryKey(autoGenerate = false)
        val id: Int? = null,
        val _links: LinksEntity? = null,
        val externals: ExternalsEntity? = null,
        val genres: List<String>? = null,
        val image: ImageEntity? = null,
        val language: String? = null,
        val name: String? = null,
        val network: NetworkEntity? = null,
        val officialSite: String? = null,
        val premiered: String? = null,
        val rating: RatingEntity? = null,
        val runtime: Int? = null,
        val schedule: ScheduleEntity? = null,
        val status: String? = null,
        val summary: String? = null,
        val type: String? = null,
        val updated: Int? = null,
        val url: String? = null,
        val webChannel: WebChannelEntity? = null,
        val weight: Int? = null
) : Parcelable