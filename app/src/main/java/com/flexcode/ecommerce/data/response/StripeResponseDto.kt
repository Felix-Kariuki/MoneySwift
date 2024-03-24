package com.flexcode.ecommerce.data.response

data class StripeResponseDto(
    val key: String,
    val clientSecret: String,
    val customerId: String,
)
