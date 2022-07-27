package com.narcis.openweatherinterview.di


import okhttp3.Interceptor
import okhttp3.Response

class QueryParameterAddInterceptor:Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            .addQueryParameter("appid", "994f5ded78c950a3f394f6cc65f83fb6")
//            .addQueryParameter("units","metric")
            .build()

        val request = chain.request().newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}