package com.musya.moviez.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.musya.moviez.data.source.local.entity.EpisodeEntity
import com.musya.moviez.data.source.local.entity.MovieEntity
import com.musya.moviez.vo.Resource

interface MoviesDataSource {
    fun getMovies(): LiveData<Resource<List<MovieEntity>>>

    fun getMovieDetail(fieldId: Int, movieId: Int): LiveData<Resource<MovieEntity>>

    fun getTvShows(): LiveData<Resource<List<MovieEntity>>>

    fun getTvDetail(fieldId: Int, tvId: Int): LiveData<Resource<MovieEntity>>

    fun getEpisodes(tvId: Int): LiveData<Resource<List<EpisodeEntity>>>

    fun getCasts(id: Int, isSeries: Boolean): LiveData<Resource<MovieEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<MovieEntity>>

    fun setFavoriteMovie(movie: MovieEntity)

    fun setFavoriteTvShow(tvShow: MovieEntity)

    fun searchMovie(query: String): LiveData<PagedList<MovieEntity>>

    fun searchTvShow(query: String): LiveData<PagedList<MovieEntity>>
}
