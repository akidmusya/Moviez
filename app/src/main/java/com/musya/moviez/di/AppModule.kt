package com.musya.moviez.di

import android.app.Application
import com.musya.moviez.data.source.MoviesRepository
import com.musya.moviez.data.source.local.LocalDataSource
import com.musya.moviez.data.source.local.room.MoviesDao
import com.musya.moviez.data.source.local.room.MoviesDatabase
import com.musya.moviez.data.source.remote.RemoteDataSource
import com.musya.moviez.utils.ApiServices
import com.musya.moviez.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    companion object{
        @Singleton
        @Provides
        fun provideMoviesDatabase(application: Application): MoviesDatabase =
            MoviesDatabase.getInstance(application)

        @Singleton
        @Provides
        fun provideMoviesDao(moviesDatabase: MoviesDatabase): MoviesDao =
            moviesDatabase.moviesDao()

        @Singleton
        @Provides
        fun provideLocalDataSource(moviesDao: MoviesDao): LocalDataSource =
            LocalDataSource(moviesDao)

        @Singleton
        @Provides
        fun provideRemoteDataSource(apiServices: ApiServices): RemoteDataSource =
            RemoteDataSource(apiServices)

        @Singleton
        @Provides
        fun provideMoviesRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): MoviesRepository = MoviesRepository(remoteDataSource, localDataSource)

        @Singleton
        @Provides
        fun provideViewModelFactory(moviesRepository: MoviesRepository): ViewModelFactory =
            ViewModelFactory(moviesRepository)
    }
}