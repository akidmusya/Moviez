package com.musya.moviez.di

import com.musya.moviez.data.source.MoviesRepository
import com.musya.moviez.data.source.remote.RemoteDataSource
import com.musya.moviez.utils.ApiServices

object Injection {

    fun provideMoviesRepository(){
        //val remoteDataSource = RemoteDataSource.getInstance(ApiServices())
        //return MoviesRepository.getInstance(remoteDataSource)
    }
}