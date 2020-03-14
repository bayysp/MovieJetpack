package com.example.jetpacksubmission.ui.tvshow

import com.example.jetpacksubmission.data.tvshow.popular.PopularResultsItem
import com.example.jetpacksubmission.data.tvshow.popular.PopularTvshowResponse

interface TvshowView {
    fun onSuccess(data : ArrayList<PopularResultsItem?>?)
}