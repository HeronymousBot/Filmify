package com.lorenzofonseca.data.use_cases

import com.lorenzofonseca.data.repositories.MovieApiRepository
import com.lorenzofonseca.domain.base.Either
import com.lorenzofonseca.domain.base.Failure
import com.lorenzofonseca.domain.base.IUseCase
import com.lorenzofonseca.domain.entities.MovieDetailModel
import com.lorenzofonseca.domain.entities.MovieResultModel

class GetMovieDetailsUseCase(private val repository: MovieApiRepository) :
    IUseCase<Either<Failure?, MovieDetailModel?>, GetMovieDetailsUseCase.Params> {
    data class Params(val id: Int)

    override suspend fun execute(parameters: GetMovieDetailsUseCase.Params): Either<Failure?, MovieDetailModel?> =
        repository.getMovie(id = parameters.id)
}