package com.lorenzofonseca.data.model

import com.google.gson.annotations.SerializedName

data class MovieResultResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("genres")
    val genres: List<GenreResponse?>?,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double
)
