package com.example.jetpacksubmission.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.jetpacksubmission.data.ApiMain
import com.example.jetpacksubmission.data.tvshow.popular.PopularResultsItem
import com.example.jetpacksubmission.data.tvshow.popular.PopularTvshowResponse
import com.example.jetpacksubmission.ui.fragment.tvshow.TvshowView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvshowViewModel : ViewModel(){
    private var listPopularData : ArrayList<PopularResultsItem?>? = ArrayList()

    fun setPopularListData(listPopularData: ArrayList<PopularResultsItem?>?){
        Log.d("TvshowViewModel","Enter setPopularListData")
        this.listPopularData = listPopularData
    }

    fun getPopularListData() : ArrayList<PopularResultsItem?>?{
        Log.d("TvshowViewModel","Enter getPopularListData")
        return listPopularData
    }

    fun setPopularTvshow(tvshowView: TvshowView) {

        Log.d("TvshowViewModel","Enter setPopularTvshow")

        ApiMain().getTvshowApi().getPopularTvshow()
            .enqueue(object : Callback<PopularTvshowResponse> {
                override fun onFailure(call: Call<PopularTvshowResponse>, t: Throwable) {
                    Log.d("TvshowViewModel","Enter setPopularListData , onFailure get data error")
                }

                override fun onResponse(
                    call: Call<PopularTvshowResponse>,
                    response: Response<PopularTvshowResponse>
                ) {
                    if (response.code() == 200) {
                        Log.d("TvshowViewModel","Enter setPopularListData, onResponse code 200")
                        val tvshowResponse = response.body()
                        tvshowView.onSuccess(tvshowResponse?.results)
                        setPopularListData(tvshowResponse?.results)
                        Log.d("TvshowViewModel", tvshowResponse?.results.toString())
                    }
                }

            })
    }
}