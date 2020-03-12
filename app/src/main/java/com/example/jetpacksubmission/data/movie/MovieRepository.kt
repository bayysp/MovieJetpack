package com.example.jetpacksubmission.data.movie

import com.example.jetpacksubmission.BuildConfig
import com.example.jetpacksubmission.data.movie.upcoming.UpcomingMovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieRepository {
    @GET(BuildConfig.TSDB_API_KEY+"movie/upcoming?api_key="+BuildConfig.API_KEY)
    fun getUpcomingMovie() : Call<UpcomingMovieResponse>
}