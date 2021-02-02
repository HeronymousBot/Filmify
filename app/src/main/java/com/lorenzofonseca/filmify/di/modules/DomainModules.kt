package com.lorenzofonseca.filmify.di.modules

import com.lorenzofonseca.data.use_cases.GetDiscoverUseCase
import com.lorenzofonseca.data.use_cases.GetMovieDetailsUseCase
import org.koin.dsl.module

internal val DomainModules = module {
    factory { GetDiscoverUseCase(get()) }
    factory { GetMovieDetailsUseCase(get()) }
}