package com.musya.moviez.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.musya.moviez.data.source.local.entity.MovieEntity
import com.musya.moviez.data.source.MoviesRepository
import com.musya.moviez.utils.DataDummy
import com.musya.moviez.vo.Resource
import com.nhaarman.mockitokotlin2.verify

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest{

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieEntity>>>

    @Before
    fun setUp(){
        viewModel = MoviesViewModel(moviesRepository)
    }

    @Test
    fun getMovies(){
        val dummyMovies = Resource.success(DataDummy.generateDummyMovies(false))
        val movies = MutableLiveData<Resource<List<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(moviesRepository.getMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value?.data
        verify(moviesRepository).getMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

}