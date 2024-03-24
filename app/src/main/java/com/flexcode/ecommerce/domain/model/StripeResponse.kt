package com.flexcode.ecommerce.domain.model

data class StripeResponse(
    val key: String,
    val clientSecret: String,
    val customerId: String,
)
