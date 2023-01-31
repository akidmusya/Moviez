package com.musya.moviez.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.musya.moviez.LiveDataTestUtil
import com.musya.moviez.PagedListUtil
import com.musya.moviez.data.source.local.LocalDataSource
import com.musya.moviez.data.source.local.entity.EpisodeEntity
import com.musya.moviez.data.source.local.entity.MovieEntity
import com.musya.moviez.data.source.remote.RemoteDataSource
import com.musya.moviez.utils.DataDummy
import com.musya.moviez.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MoviesRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val moviesRepository = FakeMoviesRepository(remote, local)

    private val listMoviesResponse = DataDummy.generateDummyMovieResponse()
    private val listTvResponse = DataDummy.generateDummyTvResponse()
    private val movieId = listMoviesResponse.results[0].id
    private val movieDetailResponse = DataDummy.generateMovieDetailResponse()
    private val tvId = listTvResponse.results[0].id
    private val tvDetailResponse = DataDummy.generateTvDetailResponse()
    private val episodesResponse = DataDummy.generateEpisodeResponse(13)

    @Test
    fun getMovies() {

        val dummyMovies = MutableLiveData<List<MovieEntity>>()
        dummyMovies.value = DataDummy.generateDummyMovies(false)
        `when`(local.getAllMovies()).thenReturn(dummyMovies)


        val movieEntities = LiveDataTestUtil.getValue(moviesRepository.getMovies())
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(listMoviesResponse.results.size, movieEntities.data?.size)
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = DataDummy.generateDummyMovies(false)[0]
        `when`(local.getMovieById(movieId)).thenReturn(dummyMovie)

        val movieEntity = LiveDataTestUtil.getValue(moviesRepository.getMovieDetail(5, movieId))
        verify(local).getMovieById(movieId)

        assertNotNull(movieEntity)
        assertEquals(movieDetailResponse.title, movieEntity.data?.title)
    }

    @Test
    fun getTvShows() {
        val dummyTvShow = MutableLiveData<List<MovieEntity>>()
        dummyTvShow.value = DataDummy.generateDummyMovies(true)
        `when`(local.getAllTvShow()).thenReturn(dummyTvShow)

        val tvShowEntities = LiveDataTestUtil.getValue(moviesRepository.getTvShows())
        verify(local).getAllTvShow()
        assertNotNull(tvShowEntities.data)
        assertEquals(listTvResponse.results.size, tvShowEntities.data?.size)
    }

    @Test
    fun getTvDetail() {
        val dummyTvShow = MutableLiveData<MovieEntity>()
        dummyTvShow.value = DataDummy.generateDummyMovies(true)[0]
        `when`(local.getTvShowById(tvId)).thenReturn(dummyTvShow)

        val tvShowEntity = LiveDataTestUtil.getValue(moviesRepository.getTvDetail(1, tvId))
        verify(local).getTvShowById(tvId)

        assertNotNull(tvShowEntity)
        assertEquals(tvDetailResponse.name, tvShowEntity.data?.title)
    }

    @Test
    fun getEpisodes() {
        val dummyEpisodes = MutableLiveData<List<EpisodeEntity>>()
        dummyEpisodes.value = DataDummy.generateEpisode(13)
        `when`(local.getEpisodes(tvId.toString())).thenReturn(dummyEpisodes)

        val episodeEntities = LiveDataTestUtil.getValue(moviesRepository.getEpisodes(tvId))
        verify(local).getEpisodes(tvId.toString())

        assertNotNull(episodeEntities)
        assertEquals(episodesResponse.episodes.size, episodeEntities.data?.size)
    }

    @Test
    fun getFavoriteMovies(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        moviesRepository.getFavoriteMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies(false)))
        verify(local).getFavoriteMovie()

        assertNotNull(movieEntity.data)
        assertEquals(listMoviesResponse.results.size, movieEntity.data?.size)
    }

    @Test
    fun getFavoriteTvShows(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteTvShow()).thenReturn(dataSourceFactory)
        moviesRepository.getFavoriteTvShows()

        val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies(true)))
        verify(local).getFavoriteTvShow()

        assertNotNull(tvShowEntity)
        assertEquals(listTvResponse.results.size, tvShowEntity.data?.size)
    }
}