package com.example.jetpacksubmission.ui.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jetpacksubmission.R
import com.example.jetpacksubmission.data.movie.detail.DetailMovieResponse
import com.example.jetpacksubmission.ui.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.item_list_upcoming.view.*

class DetailActivity : AppCompatActivity(), MovieDetailView {

    private lateinit var detailData: DetailMovieResponse
    private lateinit var viewModel: MovieViewModel
    private val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w780/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(activitydetail_toolbar)
        activitydetail_fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val idMovie: String? = intent.getStringExtra("id_movie")
        Log.d("DetailActivity", "Enter onCreate - get id_movie : $idMovie")

        detailData = DetailMovieResponse()
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MovieViewModel::class.java]

        if (viewModel.getMovieDetailData()?.id != null){
            displayDetailRotate(viewModel.getMovieDetailData()!!)
        }else{
            viewModel.setDetailMovie(this,idMovie)
        }

    }

    private fun displayDetailRotate(dataRotate : DetailMovieResponse ) {
        Log.d("DetailActivity", "Enter displayDetail")

        Glide.with(applicationContext)
            .load(BASE_IMAGE_URL + dataRotate?.backdropPath.toString())
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(activitymain_toolbarimage)

        contentdetail_tv_description.text = dataRotate?.overview.toString()
    }

    private fun displayDetail() {
        Log.d("DetailActivity", "Enter displayDetail")

        Glide.with(applicationContext)
            .load(BASE_IMAGE_URL + detailData?.backdropPath.toString())
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(activitymain_toolbarimage)

        contentdetail_tv_description.text = detailData?.overview.toString()
    }

    override fun onSuccess(data: DetailMovieResponse) {
        data?.let {
            Log.d("DetailActivity", "Enter onSuccess")
            detailData = it
            displayDetail()
        }
    }
}
