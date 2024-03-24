package com.flexcode.ecommerce

import com.flexcode.ecommerce.presentation.viewModel.EcommerceViewModel
import com.flexcode.ecommerce.utils.BaseViewModelTest
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EcommerceViewmodelTest : BaseViewModelTest() {

    private val getListingByIdRepo = GetListingByIdTestRepository()
    private val getListingsRepo = GetListingsTestRepository()
    private val stripeRepo = StripeTestRepository()
    private lateinit var viewModel: EcommerceViewModel

    @Before
    fun setup() {
        viewModel = EcommerceViewModel(
            getListingsRepository = getListingsRepo,
            getListingByIdRepository = getListingByIdRepo,
            stripePaymentRepository = stripeRepo,
        )
    }

    @Test
    fun `test get item by id updates ui respectively with success state`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.state.collect()
        }

        viewModel.getAListingById(1)
        val uiState = viewModel.state.value
        assertFalse(uiState.isLoading)
        Assert.assertNull(uiState.errorMsg)
        Truth.assertThat(uiState.singleListing).isEqualTo(
            com.flexcode.ecommerce.presentation.components.sampleItem,
        )
        collectJob.cancel()
    }

    @Test
    fun `test success listings retrieval has the correct correct success state`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.state.collect()
        }
        viewModel.getAllListings()

        val uiState = viewModel.state.value
        assertFalse(uiState.isLoading)
        assertNull(uiState.errorMsg)
        Truth.assertThat(uiState.listings.size).isEqualTo(1)
        collectJob.cancel()
    }

    @Test
    fun `test success stripe payment initialization correct success state`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.state.collect()
        }
        viewModel.initStripeBilling("10000", "usd")

        val uiState = viewModel.state.value
        assertFalse(uiState.isLoading)
        assertNull(uiState.errorMsg)
        collectJob.cancel()
    }
}
