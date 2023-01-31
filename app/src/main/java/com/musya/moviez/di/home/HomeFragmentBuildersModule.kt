package com.musya.moviez.di.home

import com.musya.moviez.di.home.favorite.FavoriteFragmentBuildersModule
import com.musya.moviez.ui.favorite.FavoriteFragment
import com.musya.moviez.ui.movies.MoviesFragment
import com.musya.moviez.ui.tvshow.TvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMoviesFragment(): MoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowFragment(): TvShowFragment

    @ContributesAndroidInjector (modules = [FavoriteFragmentBuildersModule::class])
    abstract fun contributeFavoriteFragment(): FavoriteFragment
}