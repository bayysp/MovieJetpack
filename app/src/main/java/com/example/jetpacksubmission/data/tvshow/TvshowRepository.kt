package com.example.jetpacksubmission.data.tvshow

import com.example.jetpacksubmission.BuildConfig
import com.example.jetpacksubmission.data.tvshow.detail.TvshowDetailResponse
import com.example.jetpacksubmission.data.tvshow.popular.PopularTvshowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TvshowRepository {
    @GET(BuildConfig.TSDB_API_KEY+"tv/popular?api_key="+ BuildConfig.API_KEY)
    fun getPopularTvshow() : Call<PopularTvshowResponse>

//  https://api.themoviedb.org/3/tv/93533?api_key=05faacecb1bb8a123ad56542b1708bad
    @GET(BuildConfig.TSDB_API_KEY+"tv/"+"{id}?api_key="+ BuildConfig.API_KEY)
    fun getDetailTvshow(@Path("id") id : String?) : Call<TvshowDetailResponse>
}