package com.example.jetpacksubmission.ui.viewmodel

import com.example.jetpacksubmission.data.ApiMain
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvshowViewModelTest {

    private lateinit var tvshowViewModel: TvshowViewModel

    @Before
    fun setUp(){
        tvshowViewModel = TvshowViewModel()
    }

    @Test
    fun setPopularTvshow() {
        val response = ApiMain().getTvshowApi().getPopularTvshow().execute()
        val result = response.body()
        assertNotNull(result)
        assertEquals(20,result?.results?.size)
    }

    @Test
    fun setDetailTvshow() {
        val response = ApiMain().getTvshowApi().getDetailTvshow("60735").execute()
        val result = response.body()
        assertNotNull(result)
        assertEquals("60735",result?.id.toString())
        assertEquals("The Flash",result?.originalName)
        assertEquals(6.9, result?.voteAverage)
    }
}