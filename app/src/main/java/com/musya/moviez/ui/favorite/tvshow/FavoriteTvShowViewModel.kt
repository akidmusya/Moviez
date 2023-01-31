package com.musya.moviez.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.musya.moviez.data.source.MoviesRepository
import com.musya.moviez.data.source.local.entity.MovieEntity

class FavoriteTvShowViewModel (private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getFavTvShow() : LiveData<PagedList<MovieEntity>> = moviesRepository.getFavoriteTvShows()

    fun searchTvShow(query: String): LiveData<PagedList<MovieEntity>> = moviesRepository.searchTvShow(query)
}