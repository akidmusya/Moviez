package com.musya.moviez.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.musya.moviez.data.source.local.entity.EpisodeEntity
import com.musya.moviez.data.source.local.entity.MovieEntity
import com.musya.moviez.data.source.MoviesRepository
import com.musya.moviez.vo.Resource
import javax.inject.Inject

class DetailViewModel @Inject constructor (private val moviesRepository: MoviesRepository) : ViewModel() {
    private lateinit var movieId: String

    fun setSelectedMovie(movieId: String){
        this.movieId = movieId
    }

    fun getMovie(fieldId : Int, movieId: Int): LiveData<Resource<MovieEntity>> = moviesRepository.getMovieDetail(fieldId, movieId)

    fun getTvShow(fieldId: Int, tvId: Int): LiveData<Resource<MovieEntity>> = moviesRepository.getTvDetail(fieldId, tvId)

    fun getEpisodes(tvId: Int): LiveData<Resource<List<EpisodeEntity>>> = moviesRepository.getEpisodes(tvId)

    fun getCasts(id: Int, isSeries: Boolean): LiveData<Resource<MovieEntity>> = moviesRepository.getCasts(id, isSeries)

    fun setFavoriteMovie(movie: MovieEntity) = moviesRepository.setFavoriteMovie(movie)

    fun setFavoriteTvShow(tvShow: MovieEntity) = moviesRepository.setFavoriteTvShow(tvShow)

}