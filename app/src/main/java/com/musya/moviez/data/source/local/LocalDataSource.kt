package com.musya.moviez.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.musya.moviez.data.source.local.entity.EpisodeEntity
import com.musya.moviez.data.source.local.entity.MovieEntity
import com.musya.moviez.data.source.local.room.MoviesDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val mMoviesDao: MoviesDao){

    fun getAllMovies(): LiveData<List<MovieEntity>> = mMoviesDao.getAllMovies()

    fun getAllTvShow(): LiveData<List<MovieEntity>> = mMoviesDao.getAllTvShow()

    fun getMovieById(id: Int): LiveData<MovieEntity> = mMoviesDao.getMovieById(id)

    fun getTvShowById(id: Int): LiveData<MovieEntity> = mMoviesDao.getTvShowById(id)

    fun setMovieCast(movieId:Int, cast: String) = mMoviesDao.updateMovieCast(movieId, cast)

    fun setTvShowCast(tvShowId: Int, cast: String) = mMoviesDao.updateTvShowCast(tvShowId, cast)

    fun updateMovie(movie: MovieEntity) = mMoviesDao.updateMovie(movie)

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> = mMoviesDao.getFavoriteMovie()

    fun getFavoriteTvShow(): DataSource.Factory<Int, MovieEntity> = mMoviesDao.getFavoriteTvShow()

    fun getEpisodes(tvId: String): LiveData<List<EpisodeEntity>> = mMoviesDao.getEpisodes(tvId)

    fun searchMovie(query: String): DataSource.Factory<Int, MovieEntity> = mMoviesDao.searchMovie("%$query%")

    fun searchTvShow(query: String): DataSource.Factory<Int, MovieEntity> = mMoviesDao.searchTvShow("%$query%")

    fun setEpisodes(episodes: List<EpisodeEntity>) = mMoviesDao.insertEpisodes(episodes)

    fun updateEpisodeCount(tvId: String, episodeCount: Int) = mMoviesDao.updateEpisodeCount(tvId, episodeCount)

    fun insertMovie(movies : List<MovieEntity>) = mMoviesDao.insertMovie(movies)

    fun setFavorite(movie: MovieEntity){
        movie.isFavorite = !movie.isFavorite
        mMoviesDao.updateMovie(movie)
    }
}