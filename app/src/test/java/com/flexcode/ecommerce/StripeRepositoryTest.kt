package com.flexcode.ecommerce

import com.flexcode.ecommerce.data.remote.EcommerceKtorService
import com.flexcode.ecommerce.data.repository.StripePaymentRepositoryImpl
import com.flexcode.ecommerce.domain.repository.StripePaymentRepository
import com.flexcode.ecommerce.domain.utils.ClientParser
import com.flexcode.ecommerce.domain.utils.CustomerParser
import com.flexcode.ecommerce.domain.utils.KeyParser
import com.flexcode.ecommerce.domain.utils.ResultWrapper
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class StripeRepositoryTest {

    @MockK
    val apiService = mockk<EcommerceKtorService>()
    private val customerParser = mockk<CustomerParser>()
    private val keyParser = mockk<KeyParser>()
    private val clientParser = mockk<ClientParser>()
    private lateinit var repository: StripePaymentRepository

    @Before
    fun setup() {
        repository = StripePaymentRepositoryImpl(
            ktorService = apiService,
            customerParser = customerParser,
            keyParser = keyParser,
            clientParser = clientParser,
        )
    }

    @Test
    fun `test failed  createStripePayment returns correctly mapped  error`() = runTest {
        coEvery { apiService.createStripePayment() } throws Exception("Network error")

        val result = repository.createStripePayment().last().errorMessage

        assertEquals("An error occurred Network error", result)
    }

    @Test
    fun `test createStripePayment triggers loading state first`() = runTest {
        coEvery { apiService.createStripePayment() } returns sampleStripe.toString()
        val result =
            repository.createStripePayment().first()
        assertEquals(ResultWrapper.Loading(null).data, result.data)
    }
}
