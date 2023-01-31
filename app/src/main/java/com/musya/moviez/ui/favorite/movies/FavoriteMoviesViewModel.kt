package com.musya.moviez.ui.favorite.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.musya.moviez.data.source.MoviesRepository
import com.musya.moviez.data.source.local.entity.MovieEntity

class FavoriteMoviesViewModel (private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getFavMovies() : LiveData<PagedList<MovieEntity>> = moviesRepository.getFavoriteMovies()

    fun searchMovie(query: String) : LiveData<PagedList<MovieEntity>> = moviesRepository.searchMovie(query)
}