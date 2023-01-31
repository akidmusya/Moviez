package com.musya.moviez.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.musya.moviez.data.source.local.entity.EpisodeEntity
import com.musya.moviez.data.source.local.entity.MovieEntity

@Dao
interface MoviesDao {

    @Query("SELECT * from movieEntities WHERE episodeCount = 0")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * from movieEntities WHERE episodeCount > 0")
    fun getAllTvShow(): LiveData<List<MovieEntity>>

    @Query("SELECT * from movieEntities WHERE movieId = :movieId AND episodeCount = 0")
    fun getMovieById(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * from movieEntities WHERE movieId = :tvId AND episodeCount > 0")
    fun getTvShowById(tvId: Int): LiveData<MovieEntity>

    @Query("SELECT * from movieEntities WHERE id = :id ")
    fun getFieldById(id: Int): LiveData<MovieEntity>

    @Query("SELECT * from movieEntities WHERE episodeCount = 0 AND isFavorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * from movieEntities WHERE episodeCount > 0 AND isFavorite = 1")
    fun getFavoriteTvShow(): DataSource.Factory<Int, MovieEntity>

    @Query("UPDATE movieEntities SET actors = :actors WHERE movieId = :movieId AND episodeCount = 0")
    fun updateMovieCast(movieId: Int, actors: String)

    @Query("UPDATE movieEntities SET actors = :actors WHERE movieId = :tvShowId AND episodeCount > 0")
    fun updateTvShowCast(tvShowId: Int, actors: String)

    @Query("SELECT * from episodeEntity WHERE fieldTvShowId = :tvId ORDER BY episodeNumber ASC")
    fun getEpisodes(tvId: String): LiveData<List<EpisodeEntity>>

    @Query("UPDATE movieEntities SET episodeCount = :episodeCount WHERE movieId = :tvShowId AND episodeCount > 0")
    fun updateEpisodeCount(tvShowId: String, episodeCount: Int)

    @Query("SELECT * FROM movieEntities WHERE title like :query AND episodeCount = 0 AND isFavorite = 1")
    fun searchMovie(query: String) : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieEntities WHERE title like :query AND episodeCount > 0 AND isFavorite = 1")
    fun searchTvShow(query: String) : DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = EpisodeEntity::class)
    fun insertEpisodes(episodes: List<EpisodeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovie(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movies: MovieEntity)
}