package com.lorenzofonseca.filmify.ui.movie_detail

import androidx.lifecycle.*
import androidx.lifecycle.Transformations.map
import com.lorenzofonseca.data.use_cases.GetMovieDetailsUseCase
import com.lorenzofonseca.domain.base.Failure
import com.lorenzofonseca.domain.entities.MovieDetailModel
import com.lorenzofonseca.filmify.utils.LiveDataSingleEvent
import com.lorenzofonseca.filmify.utils.triggerEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel(
    private val movieDetailsUseCase: GetMovieDetailsUseCase,
    private val movieId: Int
) : ViewModel(),
    LifecycleObserver {

    private val _movie: MutableLiveData<MovieDetailModel?> = MutableLiveData()
    val movie: LiveData<MovieDetailModel?> get() = _movie

    val title = map(_movie) { it?.title }

    val overview = map(_movie) { it?.overview }

    val releaseDate = map(_movie) { it?.releaseDate }

    val voteAverage = map(_movie) { it?.voteAverage.toString() }

    private val _onSuccessEvent: MutableLiveData<LiveDataSingleEvent<MovieDetailModel>> =
        MutableLiveData()
    val onSuccessEvent: LiveData<LiveDataSingleEvent<MovieDetailModel>> get() = _onSuccessEvent


    private val _onErrorEvent: MutableLiveData<LiveDataSingleEvent<Unit>> = MutableLiveData()
    val onErrorEvent: LiveData<LiveDataSingleEvent<Unit>> get() = _onErrorEvent


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getMovieDetails() {
        GlobalScope.launch {
            val params = GetMovieDetailsUseCase.Params(id = movieId)

            val result = movieDetailsUseCase.execute(params)

            withContext((Dispatchers.Main)) {
                result.either(::handleFailure, ::handleSuccess)
            }
        }
    }

    private fun handleFailure(failure: Failure?): Any = { _onErrorEvent.triggerEvent() }

    private fun handleSuccess(response: MovieDetailModel?) {

                val value = response as MovieDetailModel
                _movie.value = value
                _onSuccessEvent.triggerEvent(value)
    }
}