package com.example.jetpacksubmission.data.tvshow.popular

import com.google.gson.annotations.SerializedName

data class PopularTvshowResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: ArrayList<PopularResultsItem?>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)