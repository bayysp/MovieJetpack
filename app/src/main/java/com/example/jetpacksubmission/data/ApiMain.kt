package com.example.jetpacksubmission.data

import android.app.Application
import com.example.jetpacksubmission.BuildConfig
import com.example.jetpacksubmission.data.movie.MovieRepository
import com.example.jetpacksubmission.data.tvshow.TvshowRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiMain {

    fun getMovieApi(): MovieRepository {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MovieRepository::class.java)
    }

    fun getTvshowApi() : TvshowRepository{
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(TvshowRepository::class.java)
    }


}
