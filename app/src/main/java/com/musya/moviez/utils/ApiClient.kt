package com.musya.moviez.utils

import com.musya.moviez.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val httpClient = OkHttpClient.Builder().apply {

    }.build()

    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder().apply {
            client(httpClient)
            baseUrl(BuildConfig.BASE_URL_TMDB)
            addConverterFactory(GsonConverterFactory.create())
        }
    }

    val instance: ApiServices by lazy {
        retrofit
                .build()
                .create(ApiServices::class.java)
    }
}