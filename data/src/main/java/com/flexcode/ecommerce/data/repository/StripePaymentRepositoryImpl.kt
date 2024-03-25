package com.flexcode.ecommerce.data.repository

import com.flexcode.ecommerce.data.remote.EcommerceKtorService
import com.flexcode.ecommerce.domain.model.StripeResponse
import com.flexcode.ecommerce.domain.repository.StripePaymentRepository
import com.flexcode.ecommerce.domain.utils.ClientParser
import com.flexcode.ecommerce.domain.utils.CustomerParser
import com.flexcode.ecommerce.domain.utils.KeyParser
import com.flexcode.ecommerce.domain.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

const val error = "Error :"
@Singleton
class StripePaymentRepositoryImpl @Inject constructor(
    private val ktorService: EcommerceKtorService,
    private val customerParser: CustomerParser,
    private val keyParser: KeyParser,
    private val clientParser: ClientParser,
) : StripePaymentRepository {

    override suspend fun createStripePayment(
        amount: String,
        currency: String,
    ): Flow<ResultWrapper<StripeResponse>> {
        return flow {
            emit(ResultWrapper.Loading())
            try {
                val response = ktorService.createStripePayment()
                val customer = customerParser.parse(response)
                val key = withContext(Dispatchers.Default) {
                    getKey(customer[0])
                }
                val client = withContext(Dispatchers.Default) {
                    getClient(customer[0], amount, currency)
                }
                if (!key.contains(error) && !client.contains(error)) {
                    val result =
                        StripeResponse(
                            key = key,
                            clientSecret = client,
                            customerId = customer[0],
                        )
                    emit(
                        ResultWrapper.Success(
                            data = result,
                        ),
                    )
                } else {
                    emit(
                        ResultWrapper.Error(
                            error = "Error ",
                        ),
                    )
                }
            } catch (e: Exception) {
                emit(
                    ResultWrapper.Error(
                        error = "An error occurred ${e.localizedMessage}",
                        data = null,
                    ),
                )
            }
        }
    }

    override suspend fun getKey(customerID: String): String {
        return try {
            val response = ktorService.getKey(customerID)
            keyParser.parse(response)[0]
        } catch (e: Exception) {
            Timber.e("A n error occurred ${e.localizedMessage}")
            "Error ${e.localizedMessage}"
        }
    }

    override suspend fun getClient(customerID: String, amount: String, currency: String): String {
        return try {
            val response = ktorService.getClient(customerID, amount, currency)
            clientParser.parse(response)[0]
        } catch (e: Exception) {
            Timber.e("A n error occurred ${e.localizedMessage}")
            "Error ${e.localizedMessage}"
        }
    }
}
