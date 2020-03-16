package com.example.jetpacksubmission.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jetpacksubmission.R
import com.example.jetpacksubmission.data.movie.upcoming.UpcomingResultsItem
import com.example.jetpacksubmission.ui.activity.DetailActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_list_upcoming.view.*

class UpcomingMovieAdapter : RecyclerView.Adapter<UpcomingMovieAdapter.UpcomingViewHolder>() {

    private val upcomingData = ArrayList<UpcomingResultsItem?>()
    private val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/"

    inner class UpcomingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(upcomingResultsItem: UpcomingResultsItem?) {
            with(itemView) {
                item_list_upcoming_tv_title.text = upcomingResultsItem?.title.toString()
                item_list_upcoming_tv_rate.text = upcomingResultsItem?.voteAverage.toString()

                Glide.with(itemView.context)
                    .load(BASE_IMAGE_URL+upcomingResultsItem?.posterPath.toString())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(item_list_upcoming_iv_thumb)

                item_list_upcoming_cv.setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    Log.d("UpcomingViewAdapter","Send ID : "+upcomingResultsItem?.id+" to Detail Activity")
                    intent.putExtra("id_movie", upcomingResultsItem?.id.toString())

                    context.startActivity(intent)
                }
            }
        }
    }

    fun setData(items: ArrayList<UpcomingResultsItem?>) {
        Log.d("UpcomingAdapter", "Enter setData")
        upcomingData.clear()
        upcomingData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UpcomingMovieAdapter.UpcomingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_upcoming, parent, false)
        return UpcomingViewHolder(view)
    }

    override fun getItemCount(): Int = upcomingData.size

    override fun onBindViewHolder(holder: UpcomingMovieAdapter.UpcomingViewHolder, position: Int) {
        holder.bind(upcomingData[position])
    }
}