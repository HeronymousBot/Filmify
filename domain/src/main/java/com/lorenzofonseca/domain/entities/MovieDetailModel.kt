package com.lorenzofonseca.domain.entities

data class MovieDetailModel(
    val title: String,
    val id: Int,
    val posterPath: String,
    val genres: List<GenreModel>,
    val voteAverage: Double,
    val releaseDate: String,
    val overview: String
)
