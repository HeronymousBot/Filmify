package com.lorenzofonseca.filmify.ui

import com.lorenzofonseca.data.use_cases.GetMovieDetailsUseCase
import com.lorenzofonseca.domain.base.Success
import com.lorenzofonseca.filmify.ui.movie_detail.MovieDetailViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class MovieDetailViewModelTest : BaseViewModelTest() {
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase = mockk()
    private val viewModel by lazy {
        MovieDetailViewModel(getMovieDetailsUseCase, 100)
    }

    @Test
    fun `getMovieDetails -  if retrieving movie is successful, should return movie information`() =
        runBlocking {
            coEvery { getMovieDetailsUseCase.execute(any()) } returns Success(mockk(relaxed = true))

            viewModel.getMovieDetails()

            coVerify { getMovieDetailsUseCase.execute(any()) }

            Assert.assertNotNull(viewModel.onSuccessEvent.value?.peekContent())

            return@runBlocking
        }
}