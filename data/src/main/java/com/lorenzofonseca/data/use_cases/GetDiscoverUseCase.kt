package com.lorenzofonseca.data.use_cases

import com.lorenzofonseca.data.repositories.MovieApiRepository
import com.lorenzofonseca.domain.base.Either
import com.lorenzofonseca.domain.base.Failure
import com.lorenzofonseca.domain.base.IUseCase
import com.lorenzofonseca.domain.entities.DiscoverModel

class GetDiscoverUseCase(private val repository: MovieApiRepository) :
    IUseCase<Either<Failure, DiscoverModel?>, GetDiscoverUseCase.Params> {
    data class Params(val sortBy: String)

    override suspend fun execute(parameters: Params): Either<Failure, DiscoverModel?> =
        repository.getDiscover(sortBy = parameters.sortBy)
}