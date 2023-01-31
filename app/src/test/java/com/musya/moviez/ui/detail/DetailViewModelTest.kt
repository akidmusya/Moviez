package com.musya.moviez.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.musya.moviez.data.source.local.entity.EpisodeEntity
import com.musya.moviez.data.source.local.entity.MovieEntity
import com.musya.moviez.data.source.MoviesRepository
import com.musya.moviez.utils.DataDummy
import com.musya.moviez.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies(false)[0]
    private val movieId = dummyMovie.movieId
    private val dummyTv = DataDummy.generateDummyMovies(true)[0]
    private val tvShowId = dummyTv.movieId
    private val dummyEpisode = DataDummy.generateEpisode(dummyTv.episodesCount)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp(){
        viewModel = DetailViewModel(moviesRepository)
        viewModel.setSelectedMovie(movieId)
    }


    @Test
    fun getMovie() {
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = Resource.success(dummyMovie)

        `when`(moviesRepository.getMovieDetail(1, movieId.toInt())).thenReturn(movie)

        val movieEntity = viewModel.getMovie(1, movieId.toInt()).value?.data

        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity?.movieId)
        assertEquals(dummyMovie.title, movieEntity?.title)
        assertEquals(dummyMovie.genres, movieEntity?.genres)
        assertEquals(dummyMovie.actors, movieEntity?.actors)
        assertEquals(dummyMovie.synopsis, movieEntity?.synopsis)
        assertEquals(dummyMovie.rate, movieEntity?.rate ?: 0.1 , 0.00001)
        assertEquals(dummyMovie.releaseDate, movieEntity?.releaseDate)
        assertEquals(dummyMovie.posterPath, movieEntity?.posterPath)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<Resource<MovieEntity>>()
        tvShow.value = Resource.success(dummyTv)

        `when`(moviesRepository.getTvDetail(1, tvShowId.toInt())).thenReturn(tvShow)

        val tvShowEntity = viewModel.getTvShow(1, tvShowId.toInt()).value?.data

        assertNotNull(tvShowEntity)
        assertEquals(dummyTv.movieId, tvShowEntity?.movieId)
        assertEquals(dummyTv.title, tvShowEntity?.title)
        assertEquals(dummyTv.genres, tvShowEntity?.genres)
        assertEquals(dummyTv.actors, tvShowEntity?.actors)
        assertEquals(dummyTv.synopsis, tvShowEntity?.synopsis)
        assertEquals(dummyTv.rate, tvShowEntity?.rate ?: 0.1, 0.00001)
        assertEquals(dummyTv.releaseDate, tvShowEntity?.releaseDate)
        assertEquals(dummyTv.posterPath, tvShowEntity?.posterPath)
        assertEquals(dummyTv.episodesCount, tvShowEntity?.episodesCount)
    }

    @Test
    fun getEpisodes() {
        val episodes = MutableLiveData<Resource<List<EpisodeEntity>>>()
        episodes.value = Resource.success(dummyEpisode)

        `when`(moviesRepository.getEpisodes(tvShowId.toInt())).thenReturn(episodes)

        val episodeEntities = viewModel.getEpisodes(tvShowId.toInt()).value?.data

        assertNotNull(episodeEntities)
        assertEquals(6, episodeEntities?.size)
    }
}