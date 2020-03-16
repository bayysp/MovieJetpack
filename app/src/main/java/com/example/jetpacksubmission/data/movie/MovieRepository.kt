package com.example.jetpacksubmission.data.movie

import com.example.jetpacksubmission.BuildConfig
import com.example.jetpacksubmission.data.movie.detail.DetailMovieResponse
import com.example.jetpacksubmission.data.movie.upcoming.UpcomingMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieRepository {
    @GET(BuildConfig.TSDB_API_KEY+"movie/upcoming?api_key="+BuildConfig.API_KEY)
    fun getUpcomingMovie() : Call<UpcomingMovieResponse>

//  https://api.themoviedb.org/3/movie/338762?api_key=05faacecb1bb8a123ad56542b1708bad
    @GET(BuildConfig.TSDB_API_KEY+"movie/{id}?api_key="+BuildConfig.API_KEY)
    fun getMovieDetail(@Path("id") id : String?) : Call<DetailMovieResponse>
}