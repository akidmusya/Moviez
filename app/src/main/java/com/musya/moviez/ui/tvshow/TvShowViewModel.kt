package com.musya.moviez.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.musya.moviez.data.source.local.entity.MovieEntity
import com.musya.moviez.data.source.MoviesRepository
import com.musya.moviez.vo.Resource

class TvShowViewModel(private val moviesRepository: MoviesRepository): ViewModel() {

    fun getTvShow(): LiveData<Resource<List<MovieEntity>>> = moviesRepository.getTvShows()
}