package com.lorenzofonseca.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("results")
    val results: List<MovieResultResponse>,
    @SerializedName("page")
    val page: Int
)