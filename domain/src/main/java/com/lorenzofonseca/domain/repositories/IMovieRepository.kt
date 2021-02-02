package com.lorenzofonseca.domain.repositories

import com.lorenzofonseca.domain.base.Either
import com.lorenzofonseca.domain.base.Failure
import com.lorenzofonseca.domain.entities.DiscoverModel
import com.lorenzofonseca.domain.entities.MovieDetailModel
import com.lorenzofonseca.domain.entities.MovieResultModel
import com.lorenzofonseca.domain.entities.SearchModel

interface IMovieRepository {
    suspend fun getDiscover(sortBy: String): Either<Failure?, DiscoverModel?>
    suspend fun getMovie(id: Int): Either<Failure?, MovieDetailModel?>
    suspend fun getSearchResults(query : String) : Either<Failure?, SearchModel?>
    suspend fun getFavorites(): Either<Failure?, List<MovieResultModel>?>
}