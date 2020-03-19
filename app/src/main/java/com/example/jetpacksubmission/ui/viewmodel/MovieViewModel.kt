package com.example.jetpacksubmission.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.jetpacksubmission.data.ApiMain
import com.example.jetpacksubmission.data.movie.detail.DetailMovieResponse
import com.example.jetpacksubmission.data.movie.upcoming.UpcomingMovieResponse
import com.example.jetpacksubmission.data.movie.upcoming.UpcomingResultsItem
import com.example.jetpacksubmission.ui.activity.MovieDetailView
import com.example.jetpacksubmission.ui.fragment.movie.MovieView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    private var listUpcomingData: ArrayList<UpcomingResultsItem?>? = ArrayList()

    fun setUpcomingListData(listUpcomingData: ArrayList<UpcomingResultsItem?>?) {
        Log.d("MovieViewModel", "Enter setUpcomingListData")
        this.listUpcomingData = listUpcomingData
    }

    fun getUpcomingListData(): ArrayList<UpcomingResultsItem?>? {
        Log.d("MovieViewModel", "Enter getUpcomingListData")
        return listUpcomingData
    }

    fun setUpcomingMovie(movieView: MovieView) {

        Log.d("MovieViewModel", "Enter setUpcomingData")

        ApiMain().getMovieApi().getUpcomingMovie()
            .enqueue(object : Callback<UpcomingMovieResponse> {
                override fun onFailure(call: Call<UpcomingMovieResponse>, t: Throwable) {
                    Log.d(
                        "MovieViewModel",
                        "Enter setUpcomingListData , onFailure get data error, msg : " + t.message
                    )
                }

                override fun onResponse(
                    call: Call<UpcomingMovieResponse>,
                    response: Response<UpcomingMovieResponse>
                ) {
                    if (response.code() == 200) {
                        Log.d("MovieViewModel", "Enter setUpcomingListData, onResponse code 200")
                        val upcomingResponse = response.body()
                        movieView.onSuccess(upcomingResponse?.results)
                        setUpcomingListData(upcomingResponse?.results)
                        Log.d("MovieViewModel", "value upcomingResponse?.result : "+upcomingResponse?.results.toString())
                    }
                }

            })
    }

    //--------------------------------------------//

    private var listDetailData: DetailMovieResponse? = DetailMovieResponse()

    fun getMovieDetailData() : DetailMovieResponse?{
        Log.d("MovieViewModel", "Enter getDetailData , value : $listDetailData")
        return listDetailData
    }

    fun setMovieDetailData(listDetailData : DetailMovieResponse?){
        Log.d("MovieViewModel", "Enter setDetailData, value : $listDetailData")
        this.listDetailData = listDetailData
    }

    fun setDetailMovie(movieDetailView: MovieDetailView, idMovie : String?) {
        Log.d("MovieViewModel", "Enter setDetailMovie")

        ApiMain().getMovieApi().getMovieDetail(idMovie)
            .enqueue(object : Callback<DetailMovieResponse> {
                override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                    Log.d(
                        "MovieViewModel",
                        "Enter setDetailMovie , onFailure get data error, msg : " + t.message
                    )
                }

                override fun onResponse(
                    call: Call<DetailMovieResponse>,
                    response: Response<DetailMovieResponse>
                ) {
                    if (response.code() == 200) {
                        Log.d("MovieViewModel", "Enter setDetailMovie, onResponse code 200")
                        val detailMovieResponse = response.body()!!
                        movieDetailView.onSuccess(detailMovieResponse)
                        setMovieDetailData(detailMovieResponse)
                        Log.d("MovieViewModel", "value detailMovieResponse : $detailMovieResponse")
                    }else{
                        Log.d("MovieViewModel","Response Code : "+response.code())
                    }
                }

            })
    }

}


