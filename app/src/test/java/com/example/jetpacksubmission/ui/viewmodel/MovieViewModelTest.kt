package com.example.jetpacksubmission.ui.viewmodel

import android.util.Log
import com.example.jetpacksubmission.data.ApiMain
import com.example.jetpacksubmission.data.movie.detail.DetailMovieResponse
import com.example.jetpacksubmission.data.movie.upcoming.UpcomingResultsItem
import com.example.jetpacksubmission.ui.fragment.movie.MovieView
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieViewModelTest {

    private lateinit var viewModel : MovieViewModel

    @Before
    fun setUp(){
        viewModel = MovieViewModel()
    }

    @Test
    fun setUpcomingMovie() {
        val response = ApiMain().getMovieApi().getUpcomingMovie().execute()
        val result = response.body()
        assertNotNull(result)
        assertEquals(20,result?.results?.size)
    }

    @Test
    fun setDetailMovie() {
        val response = ApiMain().getMovieApi().getMovieDetail("338762").execute()
        val result = response.body()
        assertNotNull(result)
        assertEquals("338762",result?.id.toString())
        assertEquals("Bloodshot",result?.originalTitle)
        assertEquals(5.9, result?.voteAverage)
    }

}
