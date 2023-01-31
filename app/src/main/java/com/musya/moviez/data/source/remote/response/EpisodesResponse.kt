package com.musya.moviez.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class EpisodesResponse(

	@field:SerializedName("_id")
	val id: String,

	@field:SerializedName("episodes")
	val episodes: List<EpisodesItem>
)

data class EpisodesItem(

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("episode_number")
	val episodeNumber: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)
