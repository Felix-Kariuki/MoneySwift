package com.flexcode.ecommerce.data.remote

import com.flexcode.ecommerce.domain.utils.Constants.AUTHORIZATION
import com.flexcode.ecommerce.domain.utils.Constants.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.http.HttpMethod

interface EcommerceKtorService {

    suspend fun createStripePayment(): String

    suspend fun getKey(customerKey: String): String

    suspend fun getClient(
        customerKey: String,
        amount: String,
        currency: String,
    ): String

    companion object {
        fun create(): EcommerceKtorService {
            return EcommerceKtorServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                },
            )
        }
    }
}

class EcommerceKtorServiceImpl(
    private val client: HttpClient,
) : EcommerceKtorService {

    override suspend fun createStripePayment(): String {
        return client.request("$BASE_URL/v1/customers") {
            method = HttpMethod.Get
            header("Authorization", AUTHORIZATION)
        }
    }

    override suspend fun getKey(customerKey: String): String {
        return client.request("$BASE_URL/v1/ephemeral_keys") {
            method = HttpMethod.Post
            header("Authorization", AUTHORIZATION)
            parameter("customer", customerKey)
        }
    }

    override suspend fun getClient(
        customerKey: String,
        amount: String,
        currency: String,
    ): String {
        return client.request("$BASE_URL/v1/payment_intents") {
            method = HttpMethod.Post
            header("Authorization", AUTHORIZATION)
            parameter("customer", customerKey)
            parameter("amount", amount)
            parameter("currency", currency)
            parameter("automatic_payment_methods[enabled]", "true")
        }
    }
}
