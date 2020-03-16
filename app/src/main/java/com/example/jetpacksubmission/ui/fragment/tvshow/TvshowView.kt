package com.example.jetpacksubmission.ui.fragment.tvshow

import com.example.jetpacksubmission.data.tvshow.popular.PopularResultsItem

interface TvshowView {
    fun onSuccess(data : ArrayList<PopularResultsItem?>?)
}