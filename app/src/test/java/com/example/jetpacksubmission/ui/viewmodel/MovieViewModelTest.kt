package com.example.jetpacksubmission.ui.viewmodel

import android.util.Log
import com.example.jetpacksubmission.data.ApiMain
import com.example.jetpacksubmission.data.movie.detail.DetailMovieResponse
import com.example.jetpacksubmission.ui.fragment.movie.MovieView
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieViewModelTest {

    private lateinit var viewModel : MovieViewModel

    @Mock
    private lateinit var movieView: MovieView

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = MovieViewModel()
    }

    @Test
    fun setUpcomingListData() {

    }

    @Test
    fun getUpcomingListData() {
    }

    @Test
    fun setUpcomingMovie() {
        viewModel.setUpcomingMovie(movieView)

        val upcomingMovie = viewModel.getUpcomingListData()
        assertNotNull(upcomingMovie)
        assertEquals(20, upcomingMovie?.size)
    }

    @Test
    fun getMovieDetailData() {
    }

    @Test
    fun setMovieDetailData() {
    }

    @Test
    fun setDetailMovie() {
    }

}
