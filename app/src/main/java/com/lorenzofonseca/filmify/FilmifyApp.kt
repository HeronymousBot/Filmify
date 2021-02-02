package com.lorenzofonseca.filmify

import android.app.Application
import com.lorenzofonseca.filmify.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class FilmifyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@FilmifyApp)
            modules(AppModules.getModules())
        }
    }
}