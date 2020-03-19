package com.example.jetpacksubmission.ui.activity

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jetpacksubmission.R
import com.example.jetpacksubmission.data.movie.detail.DetailMovieResponse
import com.example.jetpacksubmission.data.tvshow.detail.TvshowDetailResponse
import com.example.jetpacksubmission.ui.viewmodel.TvshowViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail_tvshow.*
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.content_detail_tvshow.*

class DetailTvshowActivity : AppCompatActivity() , TvshowDetailView{

    private lateinit var detailData : TvshowDetailResponse
    private lateinit var viewModel : TvshowViewModel
    private val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w780/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tvshow)
        setSupportActionBar(activitydetailtvshow_toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val idTvshow: String? = intent.getStringExtra("id_tvshow")
        Log.d("DetailTvshowActivity", "Enter onCreate - get id_tvshow : $idTvshow")

        detailData = TvshowDetailResponse()
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[TvshowViewModel::class.java]

        if (viewModel.getTvshowDetailData()?.id != null){
            Log.d("DetailTvshowActivity", "onCreate - viewModel.getTvshowDetailData?.id not null")
            displayDetailRotate(viewModel.getTvshowDetailData()!!)
        }else{
            Log.d("DetailTvshowActivity", "onCreate - viewModel.getTvshowDetailData?.id not null")
            viewModel.setDetailTvshow(this,idTvshow)
        }
    }

    private fun displayDetailRotate(dataRotate : TvshowDetailResponse ) {
        Log.d("DetailTvshowActivity", "Enter displayDetail")

        Glide.with(applicationContext)
            .load(BASE_IMAGE_URL + dataRotate?.backdropPath.toString())
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(activitydetailtvshow_toolbarimage)

        Glide.with(applicationContext)
            .load(BASE_IMAGE_URL + dataRotate?.posterPath.toString())
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(activitydetailtvshow_iv_poster)

        contentdetailtvshow_tv_title.text = dataRotate.name.toString()
        contentdetailtvshow_tv_description.text = dataRotate.overview.toString()
        contentdetailtvshow_tv_rating.text = dataRotate?.voteAverage.toString()

        val rateFloat : Float = dataRotate.voteAverage!!.toFloat()
        contentdetailtvshow_ratingbar.rating = (rateFloat/2.0).toFloat()
    }

    private fun displayDetail() {
        Log.d("DetailTvshowActivity", "Enter displayDetail")

        Glide.with(applicationContext)
            .load(BASE_IMAGE_URL + detailData?.backdropPath.toString())
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(activitydetailtvshow_toolbarimage)

        Glide.with(applicationContext)
            .load(BASE_IMAGE_URL + detailData?.posterPath.toString())
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(activitydetailtvshow_iv_poster)

        contentdetailtvshow_tv_title.text = detailData?.name.toString()
        contentdetailtvshow_tv_rating.text = detailData?.voteAverage.toString()
        contentdetailtvshow_tv_description.text = detailData?.overview.toString()

        val rateFloat : Float = detailData.voteAverage!!.toFloat()
        contentdetailtvshow_ratingbar.rating = (rateFloat/2.0).toFloat()
    }

    override fun onSuccess(data: TvshowDetailResponse) {
        data?.let {
            Log.d("DetailTvshowActivity", "Enter onSuccess")
            detailData = it
            displayDetail()
        }
    }
}
