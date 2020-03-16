package com.example.jetpacksubmission.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jetpacksubmission.R
import com.example.jetpacksubmission.data.tvshow.popular.PopularResultsItem
import kotlinx.android.synthetic.main.item_list_upcoming.view.*

class PopularTvshowAdapter : RecyclerView.Adapter<PopularTvshowAdapter.PopularViewHolder>() {

    private val popularData = ArrayList<PopularResultsItem?>()
    private val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/"

    fun setData(items: ArrayList<PopularResultsItem?>) {
        Log.d("PopularAdapter", "Enter setData")
        popularData.clear()
        popularData.addAll(items)
        notifyDataSetChanged()
    }

    inner class PopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(popularResultsItem: PopularResultsItem?) {
            with(itemView) {
                item_list_upcoming_tv_title.text = popularResultsItem?.name.toString()
                item_list_upcoming_tv_rate.text = popularResultsItem?.voteAverage.toString()

                Glide.with(itemView.context)
                    .load(BASE_IMAGE_URL+popularResultsItem?.posterPath.toString())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(item_list_upcoming_iv_thumb)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularTvshowAdapter.PopularViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_upcoming, parent, false)
        return PopularViewHolder(view)
    }

    override fun getItemCount(): Int = popularData.size

    override fun onBindViewHolder(holder: PopularTvshowAdapter.PopularViewHolder, position: Int) {
        holder.bind(popularData[position])
    }

}