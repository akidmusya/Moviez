package com.musya.moviez.ui.favorite.tvshow

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

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowViewModelTest {

    private lateinit var viewModel: FavoriteTvShowViewModel

    private var searchQuery = "the"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Before
    fun setUp(){
        viewModel = FavoriteTvShowViewModel(moviesRepository)
    }


    @Test
    fun getFavTvShow() {
        val dummyTvShow = moviePagedList
        `when`(dummyTvShow.size).thenReturn(5)
        val tvShow = MutableLiveData<PagedList<MovieEntity>>()
        tvShow.value = dummyTvShow

        `when`(moviesRepository.getFavoriteTvShows()).thenReturn(tvShow)
        val tvShowEntity = viewModel.getFavTvShow().value
        verify(moviesRepository).getFavoriteTvShows()
        assertNotNull(tvShowEntity)
        assertEquals(5, tvShowEntity?.size)

        viewModel.getFavTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

    @Test
    fun searchTvShow(){
        val dummyTvShow = moviePagedList
        `when`(dummyTvShow.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyTvShow

        `when`(moviesRepository.searchTvShow(searchQuery)).thenReturn(movie)
        val tvShowEntity = viewModel.searchTvShow(searchQuery).value
        verify(moviesRepository).searchTvShow(searchQuery)
        assertNotNull(tvShowEntity)
        assertEquals(5, tvShowEntity?.size)

        viewModel.searchTvShow(searchQuery).observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}