package com.musya.moviez.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CastsResponse(

	@field:SerializedName("cast")
	val cast: List<CastItem>,

	@field:SerializedName("id")
	val id: Int
)

data class CastItem(

	@field:SerializedName("known_for_department")
	val knownForDepartment: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)
