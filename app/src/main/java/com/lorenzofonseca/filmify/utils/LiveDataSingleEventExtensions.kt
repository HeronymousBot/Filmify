package com.lorenzofonseca.filmify.utils

import androidx.lifecycle.MutableLiveData

fun MutableLiveData<LiveDataSingleEvent<Unit>>.triggerEvent() {
    value = LiveDataSingleEvent(Unit)
}

fun <T> MutableLiveData<LiveDataSingleEvent<T>>.triggerEvent(data: T) {
    value = LiveDataSingleEvent(data)

}

fun <T> MutableLiveData<LiveDataSingleEvent<T>>.triggerSuspendEvent(data: T) = postValue(
    LiveDataSingleEvent(data)
)

