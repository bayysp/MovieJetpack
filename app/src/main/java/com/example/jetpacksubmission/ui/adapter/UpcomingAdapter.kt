package com.example.jetpacksubmission.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpacksubmission.R
import com.example.jetpacksubmission.data.movie.upcoming.UpcomingResultsItem
import kotlinx.android.synthetic.main.item_list_upcoming.view.*

class UpcomingAdapter : RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>() {

    private val upcomingData = ArrayList<UpcomingResultsItem?>()
    private val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/"

    inner class UpcomingViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(upcomingResultsItem: UpcomingResultsItem?){
            with(itemView){
                item_list_upcoming_tv_title.text = upcomingResultsItem?.title.toString()
                item_list_upcoming_tv_rate.text = upcomingResultsItem?.voteAverage.toString()
                Glide.with(itemView.context).load(BASE_IMAGE_URL+upcomingResultsItem?.posterPath.toString()).into(item_list_upcoming_iv_thumb)
            }
        }
    }

    fun setData(items: ArrayList<UpcomingResultsItem?>){
        upcomingData.clear()
        upcomingData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UpcomingAdapter.UpcomingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_upcoming, parent, false)
        return UpcomingViewHolder(view)
    }

    override fun getItemCount(): Int = upcomingData.size

    override fun onBindViewHolder(holder: UpcomingAdapter.UpcomingViewHolder, position: Int) {
        holder.bind(upcomingData[position])
    }
}