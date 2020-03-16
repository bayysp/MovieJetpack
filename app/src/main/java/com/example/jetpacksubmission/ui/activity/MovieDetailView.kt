package com.example.jetpacksubmission.ui.activity

import com.example.jetpacksubmission.data.movie.detail.DetailMovieResponse

interface MovieDetailView {
    fun onSuccess(data : DetailMovieResponse)
}