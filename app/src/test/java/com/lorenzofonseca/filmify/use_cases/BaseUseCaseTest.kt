package com.lorenzofonseca.filmify.use_cases

import android.app.Application
import androidx.test.runner.AndroidJUnit4
import com.lorenzofonseca.filmify.helper.CoroutinesTestRule
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
abstract class BaseUseCaseTest {

    @Before
    fun preSetup() {
        MockKAnnotations.init(this)
    }
}