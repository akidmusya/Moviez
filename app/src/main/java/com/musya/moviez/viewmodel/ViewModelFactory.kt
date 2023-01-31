package com.musya.moviez.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.musya.moviez.data.source.MoviesRepository
import com.musya.moviez.ui.detail.DetailViewModel
import com.musya.moviez.ui.favorite.movies.FavoriteMoviesViewModel
import com.musya.moviez.ui.favorite.tvshow.FavoriteTvShowViewModel
import com.musya.moviez.ui.movies.MoviesViewModel
import com.musya.moviez.ui.tvshow.TvShowViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val mMoviesRepository: MoviesRepository): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        return when{
            modelClass.isAssignableFrom(MoviesViewModel::class.java) ->{
                MoviesViewModel(mMoviesRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) ->{
                TvShowViewModel(mMoviesRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) ->{
                DetailViewModel(mMoviesRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMoviesViewModel::class.java)->{
                FavoriteMoviesViewModel(mMoviesRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteTvShowViewModel::class.java)->{
                FavoriteTvShowViewModel(mMoviesRepository) as T
            }
            else->throw Throwable("Unknown ViewModel Class" + modelClass.name)
        }
    }
}