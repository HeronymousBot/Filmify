package com.lorenzofonseca.filmify.di.modules

import com.lorenzofonseca.filmify.ui.home.HomeViewModel
import com.lorenzofonseca.filmify.ui.movie_detail.MovieDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val UiModules = module {
    viewModel { HomeViewModel(get()) }
    viewModel { params -> MovieDetailViewModel(get(), params[0]) }
}