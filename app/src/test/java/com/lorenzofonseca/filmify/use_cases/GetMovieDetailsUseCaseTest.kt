package com.lorenzofonseca.filmify.use_cases

import com.lorenzofonseca.data.repositories.MovieApiRepository
import com.lorenzofonseca.data.use_cases.GetMovieDetailsUseCase
import com.lorenzofonseca.domain.base.Success
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetMovieDetailsUseCaseTest : BaseUseCaseTest() {

    private val repository: MovieApiRepository = mockk(relaxed = true)
    private val getMovieDetailsUseCase by lazy {
        GetMovieDetailsUseCase(repository)
    }

    @Test
    fun `When call is successful, should return expected object`() = runBlocking {
        val slot = slot<Int>()
        coEvery { repository.getMovie(any()) } returns Success(mockk())

        val result =
            getMovieDetailsUseCase.execute(GetMovieDetailsUseCase.Params(id = 238))

        coVerify { repository.getMovie(capture(slot)) }

        Assert.assertNotNull(result.right)
        Assert.assertEquals(238, slot.captured)

        return@runBlocking
    }

    @Test
    fun `When call returns error, should provoke Failure`() = runBlocking {
        val slot = slot<Int>()
        coEvery { repository.getMovie(any()) } returns Success(mockk())

        val result =
            getMovieDetailsUseCase.execute(GetMovieDetailsUseCase.Params(id = 238))

        coVerify { repository.getMovie(capture(slot)) }

        Assert.assertNotNull(result.left)
        Assert.assertEquals(238, slot.captured)

        return@runBlocking
    }
}