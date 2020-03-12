package com.example.jetpacksubmission.data

import android.app.Application
import com.example.jetpacksubmission.BuildConfig
import com.example.jetpacksubmission.data.movie.MovieRepository
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


}
