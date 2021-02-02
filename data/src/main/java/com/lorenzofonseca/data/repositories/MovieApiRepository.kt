package com.lorenzofonseca.data.repositories

import com.lorenzofonseca.data.MoviesApi
import com.lorenzofonseca.data.util.safeCall
import com.lorenzofonseca.data.util.toModel
import com.lorenzofonseca.domain.base.Either
import com.lorenzofonseca.domain.base.Failure
import com.lorenzofonseca.domain.entities.DiscoverModel
import com.lorenzofonseca.domain.entities.MovieDetailModel
import com.lorenzofonseca.domain.entities.MovieResultModel
import com.lorenzofonseca.domain.entities.SearchModel
import com.lorenzofonseca.domain.repositories.IMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieApiRepository(private val api: MoviesApi) : IMovieRepository {
    override suspend fun getDiscover(sortBy: String): Either<Failure, DiscoverModel?> {
        return withContext(Dispatchers.IO) {
            safeCall(
                block = { api.getDiscover(sortBy) },
                transform = { it?.let { response -> response.toModel() } })
        }
    }


    override suspend fun getMovie(id: Int): Either<Failure?, MovieDetailModel?> {
        return withContext(Dispatchers.IO) {
            safeCall(
                block = { api.getMovieDetails(id) },
                transform = { it?.let { response -> response.toModel() } })
        }
    }

    override suspend fun getSearchResults(query: String): Either<Failure?, SearchModel?> {
        return withContext(Dispatchers.IO) {
            safeCall(
                block = { api.getSearchResults(query = query) },
                transform = { it?.let { response -> response.toModel() } })
        }
    }

    override suspend fun getFavorites(): Either<Failure, List<MovieResultModel>> {
        TODO("Not yet implemented")
    }


}