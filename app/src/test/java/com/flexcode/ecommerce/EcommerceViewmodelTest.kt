package com.flexcode.ecommerce

import com.flexcode.ecommerce.presentation.sampleItem
import com.flexcode.ecommerce.presentation.viewModel.EcommerceViewModel
import com.flexcode.ecommerce.utils.BaseViewModelTest
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EcommerceViewmodelTest : BaseViewModelTest() {

    private val getListingByIdRepo = GetListingByIdTestRepository()
    private val getListingsRepo = GetListingsTestRepository()
    private lateinit var viewModel: EcommerceViewModel

    @Before
    fun setup() {
        viewModel = EcommerceViewModel(
            getListingsRepository = getListingsRepo,
            getListingByIdRepository = getListingByIdRepo,
        )
    }

    @Test
    fun `test get item by id updates ui respectively with success state`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.state.collect()
        }

        viewModel.getAListingById(1)
        val uiState = viewModel.state.value
        Assert.assertFalse(uiState.isLoading)
        Assert.assertNull(uiState.errorMsg)
        Truth.assertThat(uiState.singleListing).isEqualTo(sampleItem)
        collectJob.cancel()
    }

    @Test
    fun `test success listings retrieval has the correct correct success state`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.state.collect()
        }
        viewModel.getAllListings()

        val uiState = viewModel.state.value
        Assert.assertFalse(uiState.isLoading)
        assertNull(uiState.errorMsg)
        Truth.assertThat(uiState.listings.size).isEqualTo(1)
        collectJob.cancel()
    }
}
