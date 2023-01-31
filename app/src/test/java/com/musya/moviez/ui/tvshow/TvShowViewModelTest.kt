package com.musya.moviez.ui.tvshow

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
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieEntity>>>

    @Before
    fun setUp(){
        viewModel = TvShowViewModel(moviesRepository)
    }

    @Test
    fun getTvShow(){
        val dummyTv = Resource.success(DataDummy.generateDummyMovies(true))
        val tvShow = MutableLiveData<Resource<List<MovieEntity>>>()
        tvShow.value = dummyTv

        `when`(moviesRepository.getTvShows()).thenReturn(tvShow)
        val tvShowEntities = viewModel.getTvShow().value?.data
        verify(moviesRepository).getTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(11, tvShowEntities?.size)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}