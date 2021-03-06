package com.magiag.androidchallenge.data.database.converter

import com.google.gson.Gson

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.magiag.androidchallenge.data.entity.*
import java.util.*

class Converter {
    @TypeConverter
    fun transformToStringfromLinksEntity(value: String?): LinksEntity {
        if (value == null || value.equals("null")) return LinksEntity()
        return Gson().fromJson(value, LinksEntity::class.java)
    }

    @TypeConverter
    fun transformToLinksEntityfromString(value: LinksEntity?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun transformToStringfromExternalsEntity(value: String?): ExternalsEntity {
        if (value == null || value.equals("null")) return ExternalsEntity()
        return Gson().fromJson(value, ExternalsEntity::class.java)
    }

    @TypeConverter
    fun transformToExternalsEntityfromString(value: ExternalsEntity?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun transformToStringfromList(value: String?): List<String> {
        if (value == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun transformToListfromString(value: List<String>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun transformToStringfromImageEntity(value: String?): ImageEntity {
        if (value == null || value.equals("null")) return ImageEntity()
        return Gson().fromJson(value, ImageEntity::class.java)
    }

    @TypeConverter
    fun transformToImageEntityfromString(value: ImageEntity?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun transformToStringfromNetworkEntity(value: String?): NetworkEntity {
        if (value == null || value.equals("null")) return NetworkEntity()
        return Gson().fromJson(value, NetworkEntity::class.java)
    }

    @TypeConverter
    fun transformToNetworkEntityImageEntityfromString(value: NetworkEntity?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun transformToStringfromRatingEntity(value: String?): RatingEntity {
        if (value == null || value.equals("null")) return RatingEntity()
        return Gson().fromJson(value, RatingEntity::class.java)
    }

    @TypeConverter
    fun transformToNetworkRatingEntityfromString(value: RatingEntity?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun transformToStringfromScheduleEntity(value: String?): ScheduleEntity {
        if (value == null || value.equals("null")) return ScheduleEntity()
        return Gson().fromJson(value, ScheduleEntity::class.java)
    }

    @TypeConverter
    fun transformToNetworkScheduleEntityfromString(value: ScheduleEntity?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun transformToStringfromWebChannelEntity(value: String?): WebChannelEntity {
        if (value == null || value.equals("null")) return WebChannelEntity()
        return Gson().fromJson(value, WebChannelEntity::class.java)
    }

    @TypeConverter
    fun transformToWebChannelEntityfromString(value: WebChannelEntity?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun transformToStringfromSelfEntity(value: String?): SelfEntity {
        if (value == null || value.equals("null")) return SelfEntity()
        return Gson().fromJson(value, SelfEntity::class.java)
    }

    @TypeConverter
    fun transformToSelfEntityfromString(value: SelfEntity?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun transformToStringfromCountryEntity(value: String?): CountryEntity {
        if (value == null || value.equals("null")) return CountryEntity()
        return Gson().fromJson(value, CountryEntity::class.java)
    }

    @TypeConverter
    fun transformToCountryEntityfromString(value: CountryEntity?): String {
        return Gson().toJson(value)
    }
}
