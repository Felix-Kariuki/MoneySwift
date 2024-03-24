package com.flexcode.ecommerce

import com.flexcode.ecommerce.domain.model.StripeResponse
import com.flexcode.ecommerce.domain.repository.StripePaymentRepository
import com.flexcode.ecommerce.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StripeTestRepository : StripePaymentRepository {
    override suspend fun createStripePayment(
        amount: String,
        currency: String,
    ): Flow<ResultWrapper<StripeResponse>> {
        return flow {
            ResultWrapper.Success(
                data = sampleStripe,
            )
        }
    }

    override suspend fun getKey(customerID: String): String {
        return sampleStripe.key
    }

    override suspend fun getClient(customerID: String, amount: String, currency: String): String {
        return sampleStripe.clientSecret
    }
}

val sampleStripe =
    StripeResponse(
        key = "adadasdad",
        clientSecret = "retretretr",
        customerId = "rewrw",
    )
