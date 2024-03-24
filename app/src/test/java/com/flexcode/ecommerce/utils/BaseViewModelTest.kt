package com.flexcode.ecommerce.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

abstract class BaseViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutor: InstantTaskExecutorRule = InstantTaskExecutorRule()
}
