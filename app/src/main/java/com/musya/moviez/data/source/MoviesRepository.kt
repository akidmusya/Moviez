package com.musya.moviez.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.musya.moviez.data.source.local.LocalDataSource
import com.musya.moviez.data.source.local.entity.EpisodeEntity
import com.musya.moviez.data.source.local.entity.MovieEntity
import com.musya.moviez.data.source.remote.RemoteDataSource
import com.musya.moviez.data.source.remote.response.*
import com.musya.moviez.data.source.remote.vo.ApiResponse
import com.musya.moviez.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("DEPRECATION")
class MoviesRepository @Inject constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource
        ): MoviesDataSource{

    override fun getMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, MoviesResponse>(){
            override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localDataSource.getAllMovies()
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean {
                return (data?.size == 0 || data == null)
            }

            override fun createCall(): LiveData<ApiResponse<MoviesResponse>> = remoteDataSource.getMovies()


            override fun saveCallResult(data: MoviesResponse) {
                val movieList = ArrayList<MovieEntity>()
                for (item in data.results){
                    val movie = MovieEntity(
                            null,
                            item.id.toString(),
                            item.title,
                            item.voteAverage,
                            item.releaseDate,
                            "",
                            "",
                            "",
                            0,
                            item.posterPath,
                            0,
                            false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(fieldId: Int, movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(){
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getMovieById(movieId)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean =
                    data == null || data.actors.isBlank() || data.synopsis.isEmpty() || data.genres.isEmpty()



            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> = remoteDataSource.getMovieDetail(movieId)


            override fun saveCallResult(data: MovieDetailResponse) {
                val genres : MutableList<String> = ArrayList()
                for (genre in data.genres){
                    genres.add(genre.name)
                }

                val movie = MovieEntity(
                        fieldId,
                        data.id.toString(),
                        data.title,
                        data.voteAverage,
                        data.releaseDate,
                        data.overview,
                        "",
                        genres.joinToString(),
                        data.runtime,
                        data.posterPath,
                        0,
                        false
                )

                localDataSource.updateMovie(movie)

            }
        }.asLiveData()
    }

    override fun getCasts(id: Int, isSeries: Boolean): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, CastsResponse>(){
            override fun loadFromDB(): LiveData<MovieEntity> {
                return if(isSeries) localDataSource.getTvShowById(id) else localDataSource.getMovieById(id)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null || data.actors.isEmpty()

            override fun createCall(): LiveData<ApiResponse<CastsResponse>> = remoteDataSource.getCast(id, isSeries)

            override fun saveCallResult(data: CastsResponse) {
                val listCasts: MutableList<String> = ArrayList()
                var i = 0
                for (item in data.cast){
                    if (item.knownForDepartment == "Acting"){
                        listCasts.add(item.name)
                        i++
                        if(i >= 8) break
                    }
                }
                val result = listCasts.joinToString()
                if (isSeries) localDataSource.setTvShowCast(id, result) else localDataSource.setMovieCast(id, result)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity) {
        CoroutineScope(IO).launch {
            localDataSource.setFavorite(movie)
        }
    }

    override fun setFavoriteTvShow(tvShow: MovieEntity) {
        CoroutineScope(IO).launch {
            localDataSource.setFavorite(tvShow)
        }
    }

    override fun searchMovie(query: String): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return  LivePagedListBuilder(localDataSource.searchMovie(query), config).build()
    }

    override fun searchTvShow(query: String): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.searchTvShow(query), config).build()
    }

    override fun getTvShows(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, TvResponse>(){
            override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localDataSource.getAllTvShow()
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<TvResponse>> = remoteDataSource.getTvShow()

            override fun saveCallResult(data: TvResponse) {
                val tvList = ArrayList<MovieEntity>()
                for (item in data.results){
                    val tvItem = MovieEntity(
                            null,
                            item.id.toString(),
                            item.name,
                            item.voteAverage,
                            "",
                            "",
                            "",
                            "",
                            0,
                            item.posterPath,
                            1,
                            false
                    )
                    tvList.add(tvItem)
                }
                localDataSource.insertMovie(tvList)
            }

        }.asLiveData()
    }

    override fun getTvDetail(fieldId: Int, tvId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, TvDetailResponse>(){
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getTvShowById(tvId)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean =
                    data == null || data.title.isEmpty() || data.actors.isEmpty() || data.genres.isEmpty()

            override fun createCall(): LiveData<ApiResponse<TvDetailResponse>> {
                return  remoteDataSource.getTvShowDetail(tvId)
            }

            override fun saveCallResult(data: TvDetailResponse) {
                val genres : MutableList<String> = ArrayList()
                for (genre in data.genres){
                    genres.add(genre.name)
                }

                val tvShow = MovieEntity(
                        fieldId,
                        data.id.toString(),
                        data.name,
                        data.voteAverage,
                        data.firstAirDate,
                        data.overview,
                        "",
                        genres.joinToString(),
                        0,
                        data.posterPath,
                        1,
                        false
                )

                localDataSource.updateMovie(tvShow)
            }


        }.asLiveData()
    }

    override fun getEpisodes(tvId: Int): LiveData<Resource<List<EpisodeEntity>>> {
        return object : NetworkBoundResource<List<EpisodeEntity>, EpisodesResponse>(){
            override fun loadFromDB(): LiveData<List<EpisodeEntity>> {
                return localDataSource.getEpisodes(tvId.toString())
            }

            override fun shouldFetch(data: List<EpisodeEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<EpisodesResponse>> {
                return remoteDataSource.getEpisodes(tvId)
            }

            override fun saveCallResult(data: EpisodesResponse) {
                val episodes = ArrayList<EpisodeEntity>()
                var episodeCount = 0
                for (item in data.episodes){
                    val episode = EpisodeEntity(
                            item.id,
                            tvId.toString(),
                            item.episodeNumber,
                            item.name,
                            item.overview
                    )
                    episodes.add(episode)
                    episodeCount++
                }
                localDataSource.setEpisodes(episodes)
                localDataSource.updateEpisodeCount(tvId.toString(), episodeCount)
            }
        }.asLiveData()
    }
}