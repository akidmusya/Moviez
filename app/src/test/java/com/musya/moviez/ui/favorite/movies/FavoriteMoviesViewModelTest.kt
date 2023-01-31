package com.musya.moviez.ui.favorite.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.musya.moviez.data.source.MoviesRepository
import com.musya.moviez.data.source.local.entity.MovieEntity
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@Suppress("DEPRECATION")
@RunWith(MockitoJUnitRunner::class)
class FavoriteMoviesViewModelTest {

    private lateinit var viewModel: FavoriteMoviesViewModel
    private var searchQuery = "the"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Before
    fun setUp(){
        viewModel = FavoriteMoviesViewModel(moviesRepository)
    }


    @Test
    fun getFavMovies() {
        val dummyMovie = moviePagedList
        `when`(dummyMovie.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyMovie

        `when`(moviesRepository.getFavoriteMovies()).thenReturn(movie)
        val movieEntity = viewModel.getFavMovies().value
        verify(moviesRepository).getFavoriteMovies()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getFavMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun searchMovie() {
        val dummyMovie = moviePagedList
        `when`(dummyMovie.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyMovie

        `when`(moviesRepository.searchMovie(searchQuery)).thenReturn(movie)
        val movieEntity = viewModel.searchMovie(searchQuery).value
        verify(moviesRepository).searchMovie(searchQuery)
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.searchMovie("the").observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}