package com.musya.moviez.di

import com.musya.moviez.BuildConfig
import com.musya.moviez.utils.ApiServices
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object{

        @Singleton
        @Provides
        fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }.build()

        @Singleton
        @Provides
        fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
            baseUrl(BuildConfig.BASE_URL_TMDB)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
        }.build()

        @Provides
        fun provideMoviesApi(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)
    }
}