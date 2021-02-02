package com.lorenzofonseca.filmify.use_cases

import com.lorenzofonseca.data.repositories.MovieApiRepository
import com.lorenzofonseca.data.use_cases.GetDiscoverUseCase
import com.lorenzofonseca.domain.base.Error
import com.lorenzofonseca.domain.base.Success
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class GetDiscoverUseCaseTest : BaseUseCaseTest() {

    private val repository: MovieApiRepository = mockk(relaxed = true)
    private val getDiscoverUseCase by lazy {
        GetDiscoverUseCase(repository)
    }

    @Test
    fun `When call is successful, should return expected object`() = runBlocking {
        val slot = slot<String>()
        coEvery { repository.getDiscover(any()) } returns Success(mockk())

        val result =
            getDiscoverUseCase.execute(GetDiscoverUseCase.Params(sortBy = "popularity.desc"))

        coVerify { repository.getDiscover(capture(slot)) }

        assertNotNull(result.right)
        assertEquals("popularity.desc", slot.captured)

        return@runBlocking
    }

    @Test
    fun `When call returns error, should provoke Failure`() = runBlocking {
        val slot = slot<String>()
        coEvery { repository.getDiscover(any()) } returns Error(mockk())

        val result =
            getDiscoverUseCase.execute(GetDiscoverUseCase.Params(sortBy = "popularity.desc"))

        coVerify { repository.getDiscover(capture(slot)) }

        assertNotNull(result.left)
        assertEquals("popularity.desc", slot.captured)

        return@runBlocking
    }
}