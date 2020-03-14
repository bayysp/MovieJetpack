package com.example.jetpacksubmission.data.tvshow

import com.example.jetpacksubmission.BuildConfig
import com.example.jetpacksubmission.data.tvshow.popular.PopularTvshowResponse
import retrofit2.Call
import retrofit2.http.GET

interface TvshowRepository {
    @GET(BuildConfig.TSDB_API_KEY+"tv/popular?api_key="+ BuildConfig.API_KEY)
    fun getPopularTvshow() : Call<PopularTvshowResponse>
}