package com.musya.moviez.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("genres")
	val genres: List<MovieGenresItem>,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("runtime")
	val runtime: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("poster_path")
	val posterPath: String
)

data class MovieGenresItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)
