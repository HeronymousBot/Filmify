package com.lorenzofonseca.filmify.di.modules

import com.lorenzofonseca.data.MoviesApi
import com.lorenzofonseca.data.networking.ApiClient
import com.lorenzofonseca.data.repositories.MovieApiRepository
import com.lorenzofonseca.domain.repositories.IMovieRepository
import org.koin.dsl.bind
import org.koin.dsl.module

internal val NetworkingModules = module {
    single { ApiClient.createService(MoviesApi::class.java) }
    factory { MovieApiRepository(get()) } bind IMovieRepository::class
}