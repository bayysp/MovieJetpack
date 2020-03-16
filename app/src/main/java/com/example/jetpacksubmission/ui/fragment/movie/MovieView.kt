package com.example.jetpacksubmission.ui.fragment.movie

import com.example.jetpacksubmission.data.movie.upcoming.UpcomingResultsItem

interface MovieView {
    fun onSuccess(data: ArrayList<UpcomingResultsItem?>?)
}