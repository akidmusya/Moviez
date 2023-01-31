package com.musya.moviez.utils

import com.musya.moviez.BuildConfig
import com.musya.moviez.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("movie/popular")
    fun getMovies(@Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY): Call<MoviesResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY): Call<MovieDetailResponse>

    @GET("movie/{movie_id}/credits")
    fun getMovieCasts(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY): Call<CastsResponse>

    @GET("tv/popular")
    fun getTvShow(@Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY): Call<TvResponse>

    @GET("tv/{tv_id}")
    fun getTvDetail(@Path("tv_id") tvId: Int, @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY): Call<TvDetailResponse>

    @GET("tv/{tv_id}/credits")
    fun getTvCasts(@Path("tv_id") tvId: Int, @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY): Call<CastsResponse>

    @GET("tv/{tv_id}/season/{season_number}")
    fun getEpisodes(
        @Path("tv_id") tvId: Int, @Path("season_number") seasonNumber: Int, @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Call<EpisodesResponse>

}