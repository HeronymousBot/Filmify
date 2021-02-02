package com.lorenzofonseca.filmify.ui

import android.app.Application
import androidx.test.runner.AndroidJUnit4
import com.lorenzofonseca.filmify.helper.CoroutinesTestRule
import com.lorenzofonseca.filmify.helper.Resources
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
abstract class BaseViewModelTest {
    open lateinit var application: Application

    @get:Rule
    val testRule = CoroutinesTestRule()


    @Before
    fun preSetup() {
        application = Resources().application
        MockKAnnotations.init(this)
    }
}
