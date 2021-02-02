package com.lorenzofonseca.filmify.helper

import android.app.Application
import android.util.Log
import com.lorenzofonseca.domain.entities.DiscoverModel
import com.lorenzofonseca.domain.entities.MovieResultModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic

class Resources {
    val application : Application = mockk()

    init {
        mockkStatic(Log::class)
        every { Log.e(any(), any()) } returns 0
    }
}