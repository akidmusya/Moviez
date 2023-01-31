package com.musya.moviez.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.musya.moviez.data.source.local.entity.MovieEntity
import com.musya.moviez.data.source.MoviesRepository
import com.musya.moviez.vo.Resource

class MoviesViewModel(private val moviesRepository: MoviesRepository): ViewModel() {

    fun getMovies(): LiveData<Resource<List<MovieEntity>>> = moviesRepository.getMovies()
}