package com.lorenzofonseca.filmify.di

import com.lorenzofonseca.filmify.di.modules.DomainModules
import com.lorenzofonseca.filmify.di.modules.NetworkingModules
import com.lorenzofonseca.filmify.di.modules.UiModules

object AppModules {
    fun getModules() = listOf(
        DomainModules,
        UiModules,
        NetworkingModules
    )
}