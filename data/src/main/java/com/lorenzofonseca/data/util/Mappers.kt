package com.lorenzofonseca.data.util

import com.lorenzofonseca.data.model.*
import com.lorenzofonseca.domain.entities.*

fun DiscoverResponse.toModel() = DiscoverModel(
    page = page,
    results = results.map { it.toModel() }
)

fun MovieResultResponse.toModel() = MovieResultModel(
    title = title,
    id = id,
    posterPath = posterPath.orEmpty(),
    genres = genres?.map { it?.toModel() },
    voteAverage = voteAverage
)

fun MovieDetailResponse.toModel() = MovieDetailModel(
    title = title,
    id = id,
    posterPath = posterPath.orEmpty(),
    genres = genres.map { it.toModel() },
    voteAverage = voteAverage,
    overview = overview,
    releaseDate = releaseDate
)

fun GenreResponse.toModel() = GenreModel(id = id, name = name)

fun SearchResponse.toModel() = SearchModel(
    page = page,
    results = results.map { it.toModel() }
)