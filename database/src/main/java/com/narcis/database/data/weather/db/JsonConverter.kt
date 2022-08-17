package com.narcis.database.data.weather.db

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import javax.inject.Inject



internal class JsonConverter @Inject constructor(private val moshi : Moshi) {
    @OptIn(ExperimentalStdlibApi::class)
    inline fun <reified T> toJson(clazz: T) : String {
        return moshi.adapter<T>().toJson(clazz)
    }
    @OptIn(ExperimentalStdlibApi::class)
    inline fun <reified T> fromJson(json : String) : T? {
        return moshi.adapter<T>().fromJson(json)
    }
}