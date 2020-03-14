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

    private var listUpcomingData : ArrayList<UpcomingResultsItem?>? = ArrayList()

    fun setUpcomingListData(listUpcomingData: ArrayList<UpcomingResultsItem?>?){
        Log.d("MovieViewModel","Enter setUpcomingListData")
        this.listUpcomingData = listUpcomingData
    }

    fun getUpcomingListData() : ArrayList<UpcomingResultsItem?>?{
        Log.d("MovieViewModel","Enter getUpcomingListData")
        return listUpcomingData
    }

    fun setUpcomingMovie(movieView: MovieView) {

        Log.d("MovieViewModel","Enter setUpcomingData")

        ApiMain().getMovieApi().getUpcomingMovie()
            .enqueue(object : Callback<UpcomingMovieResponse> {
                override fun onFailure(call: Call<UpcomingMovieResponse>, t: Throwable) {
                    Log.d("MovieViewModel","Enter setUpcomingListData , onFailure get data error")
                }

                override fun onResponse(
                    call: Call<UpcomingMovieResponse>,
                    response: Response<UpcomingMovieResponse>
                ) {
                    if (response.code() == 200) {
                        Log.d("MovieViewModel","Enter setUpcomingListData, onResponse code 200")
                        val upcomingResponse = response.body()
                        movieView.onSuccess(upcomingResponse?.results)
                        setUpcomingListData(upcomingResponse?.results)
                        Log.d("MovieViewModel", upcomingResponse?.results.toString())
                    }
                }

            })
    }

}


