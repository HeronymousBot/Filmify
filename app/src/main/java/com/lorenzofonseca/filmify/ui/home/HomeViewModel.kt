package com.lorenzofonseca.filmify.ui.home

import androidx.lifecycle.*
import com.lorenzofonseca.data.use_cases.GetDiscoverUseCase
import com.lorenzofonseca.domain.base.Failure
import com.lorenzofonseca.domain.entities.DiscoverModel
import com.lorenzofonseca.domain.entities.GalleryModel
import com.lorenzofonseca.filmify.utils.LiveDataSingleEvent
import com.lorenzofonseca.filmify.utils.triggerEvent
import com.lorenzofonseca.filmify.utils.triggerSuspendEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val getDiscoverUseCase: GetDiscoverUseCase) : ViewModel() {

    private val _onGetDiscoverSuccess = MutableLiveData<LiveDataSingleEvent<GalleryModel>>()
    val onGetDiscoverSuccess: LiveData<LiveDataSingleEvent<GalleryModel>> get() = _onGetDiscoverSuccess

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getDiscoverPage() {
        GlobalScope.launch {
            for (params in listOfParams()) {
                val result = getDiscoverUseCase.execute(params)
                withContext(Dispatchers.Main) {
                    result.either(::handleFailure, ::handleSuccess)
                }

            }
        }
    }

    private fun handleSuccess(data: DiscoverModel?) {

        _onGetDiscoverSuccess.triggerSuspendEvent(GalleryModel("Movies", data?.results))
    }

    private fun handleFailure(failure: Failure): Any = Unit


    private fun listOfParams(): List<GetDiscoverUseCase.Params> {
        return listOf(
            GetDiscoverUseCase.Params("popularity.desc"),
            GetDiscoverUseCase.Params("vote_average.desc"),
            GetDiscoverUseCase.Params("release_date")
        )
    }
}