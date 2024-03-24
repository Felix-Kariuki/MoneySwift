package com.flexcode.ecommerce.domain.repository

import com.flexcode.ecommerce.domain.model.StripeResponse
import com.flexcode.ecommerce.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface StripePaymentRepository {

    suspend fun createStripePayment(
        amount: String = "220",
        currency: String = "usd",
    ): Flow<ResultWrapper<StripeResponse>>

    suspend fun getKey(customerID: String): String

    suspend fun getClient(
        customerID: String,
        amount: String,
        currency: String,
    ): String
}
