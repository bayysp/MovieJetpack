package com.example.jetpacksubmission.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jetpacksubmission.R
import com.example.jetpacksubmission.data.movie.detail.DetailMovieResponse
import com.example.jetpacksubmission.ui.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

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
            .into(activitydetail_toolbarimage)

        Glide.with(applicationContext)
            .load(BASE_IMAGE_URL + dataRotate?.posterPath.toString())
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(activitydetail_iv_poster)

        contentdetail_tv_title.text = dataRotate.title.toString()
        contentdetail_tv_description.text = dataRotate.overview.toString()
        contentdetail_tv_rating.text = dataRotate?.voteAverage.toString()

        val rateFloat : Float = dataRotate.voteAverage!!.toFloat()
        contentdetail_ratingbar.rating = (rateFloat/2.0).toFloat()
    }

    private fun displayDetail() {
        Log.d("DetailActivity", "Enter displayDetail")

        Glide.with(applicationContext)
            .load(BASE_IMAGE_URL + detailData?.backdropPath.toString())
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(activitydetail_toolbarimage)

        Glide.with(applicationContext)
            .load(BASE_IMAGE_URL + detailData?.posterPath.toString())
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(activitydetail_iv_poster)

        contentdetail_tv_title.text = detailData?.title.toString()
        contentdetail_tv_rating.text = detailData?.voteAverage.toString()
        contentdetail_tv_description.text = detailData?.overview.toString()

        val rateFloat : Float = detailData.voteAverage!!.toFloat()
        contentdetail_ratingbar.rating = (rateFloat/2.0).toFloat()
    }

    override fun onSuccess(data: DetailMovieResponse) {
        data?.let {
            Log.d("DetailActivity", "Enter onSuccess")
            detailData = it
            displayDetail()
        }
    }
}
