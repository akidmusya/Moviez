package com.musya.moviez.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("results")
	val results: List<ResultsItem>
)

data class ResultsItem(

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("poster_path")
	val posterPath: String
)
