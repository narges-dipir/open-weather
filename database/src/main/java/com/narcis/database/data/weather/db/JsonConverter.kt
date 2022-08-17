package com.narcis.database.data.weather.db

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import javax.inject.Inject

@OptIn(ExperimentalStdlibApi::class)
internal class JsonConverter @Inject constructor(private val moshi : Moshi) {
    inline fun <reified T> toJson(clazz: T) : String {
        return moshi.adapter<T>().toJson(clazz)
    }
    inline fun <reified T> fromJson(json : String) : T? {
        return moshi.adapter<T>().fromJson(json)
    }
}