package com.musya.moviez.di.home.favorite

import com.musya.moviez.ui.favorite.movies.FavoriteMoviesFragment
import com.musya.moviez.ui.favorite.tvshow.FavoriteTvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteFragmentBuildersModule {
    
    @ContributesAndroidInjector
    abstract fun contributeFavMovieFragment(): FavoriteMoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeFavTvShowFragment(): FavoriteTvShowFragment

}