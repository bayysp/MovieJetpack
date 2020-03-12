package com.example.jetpacksubmission.ui.movie

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.jetpacksubmission.data.ApiMain
import com.example.jetpacksubmission.data.movie.upcoming.UpcomingMovieResponse
import com.example.jetpacksubmission.data.movie.upcoming.UpcomingResultsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    fun setUpcomingMovie(movieView: MovieView) {

        ApiMain().getMovieApi().getUpcomingMovie()
            .enqueue(object : Callback<UpcomingMovieResponse> {
                override fun onFailure(call: Call<UpcomingMovieResponse>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(
                    call: Call<UpcomingMovieResponse>,
                    response: Response<UpcomingMovieResponse>
                ) {
                    if (response.code() == 200) {
                        val upcomingResponse = response.body()
                        movieView.onSuccess(upcomingResponse?.results)
                        Log.d("MovieViewModel", upcomingResponse?.results.toString())
                    }
                }

            })
    }

}


