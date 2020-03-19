package com.example.jetpacksubmission.ui.activity

import com.example.jetpacksubmission.data.tvshow.detail.TvshowDetailResponse

interface TvshowDetailView {
    fun onSuccess(data : TvshowDetailResponse)
}