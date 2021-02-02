package com.lorenzofonseca.domain.entities

data class MovieResultModel(
    val title: String,
    val id: Int,
    val posterPath: String,
    val genres: List<GenreModel?>?,
    val voteAverage: Double
)
