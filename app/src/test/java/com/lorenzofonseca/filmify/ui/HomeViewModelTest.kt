package com.lorenzofonseca.filmify.ui

import com.lorenzofonseca.data.use_cases.GetDiscoverUseCase
import com.lorenzofonseca.domain.base.Error
import com.lorenzofonseca.domain.base.Success
import com.lorenzofonseca.domain.entities.DiscoverModel
import com.lorenzofonseca.domain.entities.MovieResultModel
import com.lorenzofonseca.filmify.ui.home.HomeViewModel
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class HomeViewModelTest : BaseViewModelTest() {
    private val getDiscoverUseCase: GetDiscoverUseCase = mockk()
    private val viewModel by lazy {
        HomeViewModel(getDiscoverUseCase)
    }

    @Test
    fun `getDiscover -  if get discover is successful, should return movies`() = runBlocking {
        val discoverModel: DiscoverModel = mockk(relaxed = true)
        val moviesResultModel: List<MovieResultModel> = spyk()
        every { discoverModel.results } returns moviesResultModel

        coEvery { getDiscoverUseCase.execute(any()) } returns Success(discoverModel)

        viewModel.getDiscoverPage()

        coVerify { getDiscoverUseCase.execute(any()) }

        assertNotNull(viewModel.onGetDiscoverSuccess.value)

        return@runBlocking
    }

    @Test
    fun `getDiscover -  if get discover fails, should not return galleries`() = runBlocking {
        coEvery { getDiscoverUseCase.execute(any()) } returns Error(mockk())

        viewModel.getDiscoverPage()

        coVerify { getDiscoverUseCase.execute(any()) }

        assertNull(viewModel.onGetDiscoverSuccess.value?.peekContent())

        return@runBlocking
    }
}