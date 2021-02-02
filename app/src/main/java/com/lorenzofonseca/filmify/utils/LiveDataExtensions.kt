package com.lorenzofonseca.filmify.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<LiveDataSingleEvent<T>>.subscribe(owner: LifecycleOwner, onChanged: (T) -> Unit) {
    observe(owner, Observer {
        it.contentIfNotHandled?.let { content -> onChanged(content) }
    })
}

fun <T> LiveData<T>.observe(owner: LifecycleOwner, onChanged: (T) -> Unit){
    observe(owner, Observer {onChanged(it)})
}
